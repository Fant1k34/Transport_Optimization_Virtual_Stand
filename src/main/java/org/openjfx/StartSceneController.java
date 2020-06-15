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

        if (costsData.size() != planData.size()){
            infoLine.setText("Critical Error. Amount of ROWS in PLAN and COSTS do not equals");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        if (planData.size() >= 10 || planData.get(0).size() >= 10){
            infoLine.setText("Error. The maximum amount of rows and columns are 9");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }


        if (!planData.stream().mapToInt(i -> i.size()).allMatch(i -> i == planData.get(0).size())){
            infoLine.setText("Critical Error. There are different amount of numbers in rows in PLAN");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        if (!costsData.stream().mapToInt(i -> i.size()).allMatch(i -> i == costsData.get(0).size())){
            infoLine.setText("Critical Error. There are different amount of numbers in rows in COSTS");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        if (costsData.get(0).size() != planData.get(0).size()){
            infoLine.setText("Critical Error. Amount of COLUMNS in PLAN and COSTS do not equals");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        for (ArrayList el: planData){
            if (el.stream().mapToInt(i -> (Integer) i).sum() == 0){
                infoLine.setText("Error. PLAN must not have the ROW with 0");
                infoLine.setTextFill(Color.web("#FF8C00"));
                return;
            }
        }

        for (int j = 0; j < planData.get(0).size(); j++) {
            int sum = 0;
            for (int i = 0; i < planData.size(); i++) {
                sum += planData.get(i).get(j);
            }
            if (sum == 0) {
                infoLine.setText("Error. PLAN must not have the COLUMN with 0");
                infoLine.setTextFill(Color.web("#FF8C00"));
                return;
            }
        }

        infoLine.setText("Primary check is successful");
        infoLine.setTextFill(Color.web("#00FF00"));

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
            infoLine.setText("Error. CONSUMERS A, PROVIDERS B and COSTS must not be empty for NorthWestPlan");
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
            infoLine.setText("Error. CONSUMERS A and PROVIDERS B must be a number divided by whitespace");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        if (A.stream().reduce((r, l) -> r*l).get() == 0){
            infoLine.setText("Error. CONSUMERS A must not contain 0 elements");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        if (B.stream().reduce((r, l) -> r*l).get() == 0){
            infoLine.setText("Error. PROVIDERS B must not contain 0 elements");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        ArrayList<ArrayList<Integer>> costsData = getInformationFromLine.getInfo(text3);
        if (costsData == null) {
            infoLine.setText("Critical Error. Check your COSTS");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        if (costsData.size() >= 10 || costsData.get(0).size() >= 10){
            infoLine.setText("Error. The maximum amount of rows and columns are 9");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        if (!costsData.stream().mapToInt(i -> i.size()).allMatch(i -> i == costsData.get(0).size())){
            infoLine.setText("Critical Error. There are different amount of numbers in rows in COSTS");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        if (costsData.size() != B.size()){
            infoLine.setText("Critical Error. Check your COSTS. It must equals to PROVIDERS B");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        if (costsData.get(0).size() != A.size()){
            infoLine.setText("Critical Error. Check your COSTS. It must equals to CONSUMERS A");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        infoLine.setText("Primary check is successful");
        infoLine.setTextFill(Color.web("#00FF00"));

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
            infoLine.setText("Error. CONSUMERS A, PROVIDERS B and COSTS must not be empty for MinimalCostPlan");
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
            infoLine.setText("Error. CONSUMERS A and PROVIDERS B must be a number divided by whitespace");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        ArrayList<ArrayList<Integer>> costsData = getInformationFromLine.getInfo(text3);
        if (costsData == null) {
            infoLine.setText("Critical Error. Check your COSTS");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        if (costsData.size() >= 10 || costsData.get(0).size() >= 10){
            infoLine.setText("Error. The maximum amount of rows and columns are 9");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        if (!costsData.stream().mapToInt(i -> i.size()).allMatch(i -> i == costsData.get(0).size())){
            infoLine.setText("Critical Error. There are different amount of numbers in rows in COSTS");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        if (A.stream().reduce((r, l) -> r*l).get() == 0){
            infoLine.setText("Error. CONSUMERS A must not contain 0 elements");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        if (B.stream().reduce((r, l) -> r*l).get() == 0){
            infoLine.setText("Error. PROVIDERS B must not contain 0 elements");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        if (costsData.size() != B.size()){
            infoLine.setText("Critical Error. Check your COSTS. It must equals to PROVIDERS B");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        if (costsData.get(0).size() != A.size()){
            infoLine.setText("Critical Error. Check your COSTS. It must equals to CONSUMERS A");
            infoLine.setTextFill(Color.web("#FF8C00"));
            return;
        }

        infoLine.setText("Primary check is successful");
        infoLine.setTextFill(Color.web("#00FF00"));

        MinimalCostPlan MCPlan = new MinimalCostPlan(A, B, costsData);
        App contin = new App();
        try {
            contin.moveOn(MCPlan.getPlan(), costsData);
        } catch (Exception e) {
            System.out.println("Невозможно расставить потенциалы");
        }
    }

}
