package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MainSceneController {

    public Button addColomn;
    public Button addRow;
    public Button removeColomn;
    public Button removeRow;
    public TableView StartTable;
    public Button mainButton;

    public void buttonClicked() {
        System.out.println("Button clicked!");
    }

    public void addNewColomn(){
        ObservableList<String> people = FXCollections.observableArrayList(

                "A",
                "B",
                "C"
        );

        TableColumn<String, String> userNameCol //
                = new TableColumn<String, String>("User Name");
        StartTable = new TableView<String>(people);

        FlowPane root = new FlowPane(10, 10, StartTable);

        Scene scene = new Scene(root, 300, 250);


    }

    public void addNewRow(){

    }

    public void removeNewColomn(){

    }

    public void removeNewRow(){

    }
}