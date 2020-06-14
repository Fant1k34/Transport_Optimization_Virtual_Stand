package org.openjfx;

import java.util.ArrayList;
import java.util.Arrays;
/*
This class let you get A - buyers, B - providers and the costs matrix
 */

public class Data {
    public ArrayList<Integer> A;
    public ArrayList<Integer> B;
    public ArrayList<Integer> getA(){
        ArrayList<Integer> A = new ArrayList<Integer>();
        A.add(50);
        A.add(50);
        A.add(230);
        A.add(170);
        this.A = A;
        return A;
    }

    public ArrayList<Integer> getB(){
        ArrayList<Integer> B = new ArrayList<Integer>();
        B.add(120);
        B.add(200);
        B.add(180);
        this.B = B;
        return B;
    }

//    public ArrayList<ArrayList<Integer>> getCosts(){
//        ArrayList<ArrayList<Integer>> costs = new ArrayList<>();
//        for (int i = 0; i < B.size(); i++){
//            ArrayList<Integer> toAddToZeroPlan = new ArrayList<>();
//            for (int j = 0; j < A.size(); j++){
//                toAddToZeroPlan.add(7);
//            }
//            costs.add(toAddToZeroPlan);
//        }
//        return costs;
//    }

    public ArrayList<ArrayList<Integer>> getCosts(){
        ArrayList<Integer> line1 = new ArrayList<>(Arrays.asList(20, 27, 24, 12));
        ArrayList<Integer> line2 = new ArrayList<>(Arrays.asList(3, 32, 15, 18));
        ArrayList<Integer> line3 = new ArrayList<>(Arrays.asList(6, 30, 35, 8));
        ArrayList<ArrayList<Integer>> costs = new ArrayList<>(Arrays.asList(line1, line2, line3));
        return costs;
    }

}
