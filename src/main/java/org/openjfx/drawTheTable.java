package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class drawTheTable {
    public drawTheTable(){

    }

    public static TableView drawTable(ArrayList<ArrayList<Integer>> StartPlan, ArrayList<Integer> A, ArrayList<Integer> B){
        ObservableList<ClassForRow> info = FXCollections.observableArrayList();

        for (int i = 0; i < StartPlan.size(); i++){
            info.add(new ClassForRow(StartPlan.get(i), B.get(i)));
        }

        TableView table = new TableView(info);
        table.setPrefWidth(250);
        table.setPrefHeight(200);

        TableColumn<ClassForRow, String> Column = new TableColumn<ClassForRow, String>("");
        Column.setCellValueFactory(new PropertyValueFactory<ClassForRow, String>("V"));
        table.getColumns().add(Column);

        for (int i = 0; i < StartPlan.get(0).size(); i++) {
            TableColumn<ClassForRow, String> VColumn = new TableColumn<ClassForRow, String>(String.valueOf(A.get(i)));
            VColumn.setCellValueFactory(new PropertyValueFactory<ClassForRow, String>("V" + String.valueOf(i)));
            table.getColumns().add(VColumn);
        }
        return table;
    }
}
