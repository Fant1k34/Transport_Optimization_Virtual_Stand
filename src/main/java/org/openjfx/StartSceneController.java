package org.openjfx;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;

public class StartSceneController {


    public TextArea plan;
    public TextArea costs;
    public Button optimizedButton;
    public TextField consumers;
    public TextField providers;
    public Button northWestPlan;
    public Button minimalCosts;
    public Label infoLine;

    public void getOptimizedSolutionFromThePlan() throws IOException {
        String text1 = "";
        String text2 = "";

        try {
            text1 = plan.getText();
            text2 = costs.getText();
        }
        catch (Exception e){
            infoLine.setText("Error. PLAN and COSTS must not be empty for optimization");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        ArrayList<ArrayList<Integer>> planData = getInformationFromLine.getInfo(text1);
        if (planData == null) {
            infoLine.setText("Critical Error. Check your PLAN");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }
        ArrayList<ArrayList<Integer>> costsData = getInformationFromLine.getInfo(text2);
        if (costsData == null) {
            infoLine.setText("Critical Error. Check your COSTS");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        App contin = new App();
        contin.moveOn(planData, costsData);

    }

    public void getNorthWestPlan() {
        String text1 = "";
        String text2 = "";
        String text3 = "";
        try {
            text1 = consumers.getText();
            text2 = providers.getText();
            text3 = costs.getText();
        }
        catch (Exception e){
            infoLine.setText("Error. PROVIDERS A, CONSUMERS B and COSTS must not be empty for NorthWestPlan");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }
        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();

        try {
            A = getInformationFromLine.getLine(text1);
            B = getInformationFromLine.getLine(text2);
        }
        catch (Exception e){
            infoLine.setText("Error. PROVIDERS A and CONSUMERS B must be a number divided by whitespace");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        ArrayList<ArrayList<Integer>> costsData = getInformationFromLine.getInfo(text3);
        if (costsData == null) {
            infoLine.setText("Critical Error. Check your COSTS");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }


        NorthWestPlan NWPlan = new NorthWestPlan(A, B, costsData);
        App contin = new App();
        try {
            contin.moveOn(NWPlan.getPlan(), costsData);
        } catch (Exception e) {
            System.out.println("Невозможно расставить потенциалы");
        }
    }

    public void getMinimalCosts() {
        String text1 = "";
        String text2 = "";
        String text3 = "";
        try {
            text1 = consumers.getText();
            text2 = providers.getText();
            text3 = costs.getText();
        }
        catch (Exception e){
            infoLine.setText("Error. PROVIDERS A, CONSUMERS B and COSTS must not be empty for MinimalCostPlan");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }
        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();

        try {
            A = getInformationFromLine.getLine(text1);
            B = getInformationFromLine.getLine(text2);
        }
        catch (Exception e){
            infoLine.setText("Error. PROVIDERS A and CONSUMERS B must be a number divided by whitespace");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        ArrayList<ArrayList<Integer>> costsData = getInformationFromLine.getInfo(text3);
        if (costsData == null) {
            infoLine.setText("Critical Error. Check your COSTS");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }


        MinimalCostPlan MCPlan = new MinimalCostPlan(A, B, costsData);
        App contin = new App();
        try {
            contin.moveOn(MCPlan.getPlan(), costsData);
        } catch (Exception e) {
            System.out.println("Невозможно расставить потенциалы");
        }
    }

}
