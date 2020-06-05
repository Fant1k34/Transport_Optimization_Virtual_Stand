package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
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
        ultra.add(new ArrayList<>(Arrays.asList(0, 20, 150)));
        ultra.add(new ArrayList<>(Arrays.asList(0, 260, 0)));
        ultra.add(new ArrayList<>(Arrays.asList(60, 10, 0)));

        MethodPotential method = new MethodPotential(ultra, costs);
        method.getOptimizedSolution();
//
//        HashMap<Integer, Integer> map = new HashMap<>();
//        map.put(0, 0);
//        map.put(1, 1);

        launch();

    }

}