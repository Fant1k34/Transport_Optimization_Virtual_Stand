package org.openjfx;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

    public void getOptimizedSolutionFromThePlan() throws IOException {
        String text1 = plan.getText();
        String text2 = costs.getText();

        ArrayList<ArrayList<Integer>> planData = getInformationFromLine.getInfo(text1);
        if (planData == null) return;
        ArrayList<ArrayList<Integer>> costsData = getInformationFromLine.getInfo(text2);
        if (costsData == null) return;

        App contin = new App();
        contin.moveOn(planData, costsData);

    }

    public void getNorthWestPlan() {
        String text1 = consumers.getText();
        String text2 = providers.getText();
        String text3 = costs.getText();

        ArrayList<Integer> A = getInformationFromLine.getLine(text1);
        ArrayList<Integer> B = getInformationFromLine.getLine(text2);
        ArrayList<ArrayList<Integer>> costsData = getInformationFromLine.getInfo(text3);
        if (costsData == null) return;


        NorthWestPlan NWPlan = new NorthWestPlan(A, B, costsData);
        App contin = new App();
        try {
            contin.moveOn(NWPlan.getPlan(), costsData);
        } catch (Exception e) {
            System.out.println("Невозможно расставить потенциалы");
        }
    }

    public void getMinimalCosts() {
        String text1 = consumers.getText();
        String text2 = providers.getText();
        String text3 = costs.getText();

        ArrayList<Integer> A = getInformationFromLine.getLine(text1);
        ArrayList<Integer> B = getInformationFromLine.getLine(text2);
        ArrayList<ArrayList<Integer>> costsData = getInformationFromLine.getInfo(text3);
        if (costsData == null) return;


        MinimalCostPlan MCPlan = new MinimalCostPlan(A, B, costsData);
        App contin = new App();
        try {
            contin.moveOn(MCPlan.getPlan(), costsData);
        } catch (Exception e) {
            System.out.println("Невозможно расставить потенциалы");
        }
    }

}
