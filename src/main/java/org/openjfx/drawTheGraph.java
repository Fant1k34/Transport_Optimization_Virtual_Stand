package org.openjfx;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class drawTheGraph {
    public static Group drawGraph(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<ArrayList<Integer>> StartPlan){
        ArrayList<Circle> circles1 = new ArrayList<>();
        for (int i = 0; i < StartPlan.get(0).size(); i++){
            Circle circle = new Circle(20 + i * 40, 20, 15);
            circle.setFill(Color.BISQUE);
            circles1.add(circle);
        }
        ArrayList<Circle> circles2 = new ArrayList<>();
        for (int i = 0; i < StartPlan.size(); i++){
            Circle circle = new Circle(20 + i * 40, 100, 15);
            circle.setFill(Color.DEEPSKYBLUE);
            circles2.add(circle);
        }
        ArrayList<Line> lines = new ArrayList<>();
        for (int i = 0; i < StartPlan.get(0).size(); i++){
            for (int j = 0; j < StartPlan.size(); j++){
                if (StartPlan.get(j).get(i) > 0){
                    Line line = new Line();
                    line.setStartX(circles1.get(i).getCenterX());
                    line.setStartY(circles1.get(i).getCenterY());
                    line.setEndX(circles2.get(j).getCenterX());
                    line.setEndY(circles2.get(j).getCenterY());
                    lines.add(line);
                }
            }
        }




        Group root = new Group();
        root.getChildren().addAll(circles1);
        root.getChildren().addAll(circles2);
        root.getChildren().addAll(lines);
        return root;
    }
}
