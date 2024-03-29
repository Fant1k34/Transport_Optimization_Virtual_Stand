package org.openjfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;


/**
 * JavaFX App
 */
public class App extends Application {
    private MethodPotential method = null;
    private String startLabelText = "would be counted if optimization is possible";
    private String finishLabelText = "would be counted if optimization is possible";
    private String commonText = "Potentials could be counted";

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Method of potentials");

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/startScene.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    public void mainStep(ArrayList<ArrayList<Integer>> StartPlan, ArrayList<ArrayList<Integer>> StartCosts) throws IOException {
        Stage stage = new Stage();
        stage.setWidth(800);
        stage.setHeight(800);
        Button but = new Button("Try to optimize");


        stage.setScene(moveOn(StartPlan, StartCosts, null, null, but));

        MethodPotential method = new MethodPotential(StartPlan, StartCosts);
        this.method = method;

        try {
            method.getOptimizedSolution();
            this.startLabelText = String.valueOf(method.getStartValue());
            this.finishLabelText = String.valueOf(method.getFinishValue());
        }
        catch (Exception ex){
            commonText = "Potentials are unavailable to count. It's random";
        }

        ArrayList<Integer> stepNumber = new ArrayList<>();
        stepNumber.add(0);


        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Шаг, который надо показать
                    if (stepNumber.get(0) >= method.getSteps().size()) {
                        but.setText("Optimization is maximum");
                        return;
                    }
                    ArrayList<ArrayList<Integer>> stepToShow = getInformationFromLine.getInfo(method.getSteps().get(stepNumber.get(0)).replace("[", "")
                            .replace("]", "").replace(",", " "));
                    ArrayList<Integer> UstepToShow = getInformationFromLine.getLine(method.getVsteps().get(stepNumber.get(0) + 1).replace("[", "")
                            .replace("]", "").replace(",", " "));
                    ArrayList<Integer> VstepToShow = getInformationFromLine.getLine(method.getUsteps().get(stepNumber.get(0) + 1).replace("[", "")
                            .replace("]", "").replace(",", " "));
                    stage.setScene(moveOn(stepToShow, StartCosts, UstepToShow, VstepToShow, but));
                    stepNumber.set(0, stepNumber.get(0) + 1);
                } catch (IOException e) {
                    return;
                }
                stage.show();
            }
        });
        stage.show();
    }


    public Scene moveOn(ArrayList<ArrayList<Integer>> StartPlan, ArrayList<ArrayList<Integer>> StartCosts, ArrayList<Integer> X, ArrayList<Integer> Y, Button but) throws IOException {
        Data getItems = new Data(StartPlan, StartCosts);
        ArrayList<Integer> A = getItems.getA();
        ArrayList<Integer> B = getItems.getB();
        ArrayList<ArrayList<Integer>> costs = getItems.getCosts();

        try{
            MethodPotential method = new MethodPotential(StartPlan, StartCosts);
            method.getOptimizedSolution();
            X = getInformationFromLine.getLine(method.getVsteps().get(0).replace("[", "")
                    .replace("]", "").replace(",", " "));
            Y = getInformationFromLine.getLine(method.getUsteps().get(0).replace("[", "")
                    .replace("]", "").replace(",", " "));
        }
        catch (Exception ex){
            commonText = "Potentials are unavailable to count. It's random";
            X = A;
            Y = B;
        }

        HBox hbox = new HBox();
        VBox vbox1 = new VBox();
        VBox vbox2 = new VBox();
        VBox vbox3 = new VBox();
        VBox vbox4 = new VBox();
        vbox3.getChildren().addAll(but, new Label("Primary cost - " + startLabelText), new Label("Final cost - " + finishLabelText));
        vbox4.getChildren().addAll(forGraph.drawElement1(A, B, StartPlan), drawTheGraph.drawGraph(A, B, StartPlan), forGraph.drawElement2(A, B, StartPlan), new Label(commonText));

        TableView table1 = drawTheTable.drawTable(StartPlan, A, B);
        TableView table2 = drawTheTable.drawTable(StartCosts, X, Y);

        vbox1.getChildren().addAll(table1, vbox3);
        vbox2.getChildren().addAll(table2, vbox4);
        hbox.getChildren().addAll(vbox1, vbox2);
        return new Scene(hbox);
    }
}