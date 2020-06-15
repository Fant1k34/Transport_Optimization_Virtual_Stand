package org.openjfx;

import javafx.application.Application;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
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

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Method of potentials");

//        InputStream iconStream = getClass().getResourceAsStream("/icon.png");
//        Image image = new Image(iconStream);
//        stage.getIcons().add(image);

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/startScene.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Label newLabel = new Label("Аааааа");

        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void moveOn(ArrayList<ArrayList<Integer>> StartPlan, ArrayList<ArrayList<Integer>> StartCosts) throws IOException {

        Data getItems = new Data(StartPlan, StartCosts);
        ArrayList<Integer> A = getItems.getA();
        ArrayList<Integer> B = getItems.getB();
        ArrayList<ArrayList<Integer>> costs = getItems.getCosts();


//        MethodPotential method = new MethodPotential(StartPlan, StartCosts);
//        method.getOptimizedSolution();


        Stage stage = new Stage();


        HBox hbox = new HBox();
        VBox vbox1 = new VBox();
        VBox vbox2 = new VBox();

        TableView table1 = drawTheTable.drawTable(StartPlan, A, B);
        TableView table2 = drawTheTable.drawTable(StartCosts, A, B);

        vbox1.getChildren().addAll(table1, new Label(" 5 "));
        vbox2.getChildren().addAll(table2, new Label(" 9 "));
        hbox.getChildren().addAll(vbox1, vbox2);

//        FXMLLoader loader = new FXMLLoader();
//        URL xmlUrl = getClass().getResource("/mainScene.fxml");
//        loader.setLocation(xmlUrl);
//        Parent root = null;
//        root = loader.load();


        stage.setScene(new Scene(hbox));
        stage.setWidth(800);
        stage.setHeight(800);
        stage.show();

    }

}