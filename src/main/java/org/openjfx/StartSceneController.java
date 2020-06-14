package org.openjfx;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.ArrayList;

public class StartSceneController {


    public TextArea plan;
    public TextArea costs;
    public Button optimizedButton;

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

}
