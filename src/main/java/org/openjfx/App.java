package org.openjfx;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

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


        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        Data getItems = new Data();
        ArrayList<Integer> A = getItems.getA();
        ArrayList<Integer> B = getItems.getB();
        ArrayList<ArrayList<Integer>> costs = getItems.getCosts();

        NorthWestPlan plan = new NorthWestPlan(A, B, costs);
        MinimalCostPlan newplan = new MinimalCostPlan(A, B, costs);
        System.out.println(plan.getPlan());
        newplan.getPlan();
        plan.getSolutionPlan();
        plan.getSolutionMatrix();

        ArrayList<ArrayList<Integer>> ultra = new ArrayList<>();
        ultra.add(new ArrayList<>(Arrays.asList(0, 40, 80, 0)));
        ultra.add(new ArrayList<>(Arrays.asList(50, 0, 150, 0)));
        ultra.add(new ArrayList<>(Arrays.asList(0, 10, 0, 170)));

        MethodPotential method = new MethodPotential(ultra, costs);
        method.getOptimizedSolution();
//
//        HashMap<Integer, Integer> map = new HashMap<>();
//        map.put(0, 0);
//        map.put(1, 1);

        launch();

    }

    public void moveOn() throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/mainScene.fxml");
        loader.setLocation(xmlUrl);
        Parent root = null;
        root = loader.load();



        stage.setScene(new Scene(root));
        stage.show();

    }

}