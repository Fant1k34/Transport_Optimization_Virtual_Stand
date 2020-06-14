package org.openjfx;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class StartSceneController {


    public TextArea plan;
    public TextArea costs;
    public Button optimizedButton;

    public void getOptimizedSolutionFromThePlan() throws IOException {
        String text = plan.getText();
        System.out.println(text);


        App contin = new App();
        contin.moveOn();

    }

}
