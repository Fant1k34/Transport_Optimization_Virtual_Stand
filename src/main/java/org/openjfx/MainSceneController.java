package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainSceneController {

    public Button mainButton;

    public void buttonClicked() {
        System.out.println("Button clicked!");
        mainButton.setText("Меня нажали!");
    }

}