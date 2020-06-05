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
        A.add(250);
        A.add(100);
        A.add(150);
        this.A = A;
        return A;
    }

    public ArrayList<Integer> getB(){
        ArrayList<Integer> B = new ArrayList<Integer>();
        B.add(240);
        B.add(40);
        B.add(220);
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
        ArrayList<Integer> line1 = new ArrayList<>(Arrays.asList(9, 26, 20));
        ArrayList<Integer> line2 = new ArrayList<>(Arrays.asList(3, 14, 5));
        ArrayList<Integer> line3 = new ArrayList<>(Arrays.asList(11, 24, 17));
        ArrayList<ArrayList<Integer>> costs = new ArrayList<>(Arrays.asList(line1, line2, line3));
        return costs;
    }

}
