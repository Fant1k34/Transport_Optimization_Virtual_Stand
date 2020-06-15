package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainSceneController {
    public ArrayList<ArrayList<Integer>> StartPlan;
    public ArrayList<ArrayList<Integer>> StartCosts;

    public MainSceneController(ArrayList<ArrayList<Integer>> StartPlan, ArrayList<ArrayList<Integer>> StartCosts){
        this.StartPlan = StartPlan;
        this.StartCosts = StartCosts;
    }

    public void nextStep(){
        MethodPotential method = new MethodPotential(StartPlan, StartCosts);
        try {
            method.getOptimizedSolution();
        }
        catch (Exception ex){
            return;
        }

    }

}