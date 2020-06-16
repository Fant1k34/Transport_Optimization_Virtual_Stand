package org.openjfx;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class forGraph {
    public static HBox drawElement1(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<ArrayList<Integer>> StartPlan){
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        ArrayList<Button> buttons1 = new ArrayList<>();
        for (int i = 0 ; i < A.size(); i++){
            Button btn1 = new Button(String.valueOf(A.get(i)));
            buttons1.add(btn1);
        }

        hbox.getChildren().addAll(buttons1);
        return hbox;
    }
    public static HBox drawElement2(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<ArrayList<Integer>> StartPlan){
        HBox hbox = new HBox();
        hbox.setSpacing(7);
        ArrayList<Button> buttons2 = new ArrayList<>();
        for (int i = 0 ; i < B.size(); i++){
            Button btn1 = new Button(String.valueOf(B.get(i)));
            buttons2.add(btn1);
        }

        hbox.getChildren().addAll(buttons2);
        return hbox;
    }
}
