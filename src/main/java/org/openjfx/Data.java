package org.openjfx;

import java.util.ArrayList;
import java.util.Arrays;
/*
This class let you get A - buyers, B - providers and the costs matrix
 */

public class Data {
    public ArrayList<Integer> A = new ArrayList<>();
    public ArrayList<Integer> B = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> costs = new ArrayList<>();

    public void Data(ArrayList<ArrayList<Integer>> StartPlan, ArrayList<ArrayList<Integer>> StartCosts){
        for (int j = 0; j < StartPlan.get(0).size(); j++){
            int sum = 0;
            for (int i = 0; i < StartPlan.size(); i++){
                sum += StartPlan.get(i).get(j);
            }
            this.A.add(sum);
        }

        for (int j = 0; j < StartPlan.size(); j++) {
            int sum = 0;
            for (int i = 0; i < StartPlan.get(0).size(); i++) {
                sum += StartPlan.get(j).get(i);
            }
            this.B.add(sum);
        }
        this.costs = StartCosts;

    }


    public ArrayList<Integer> getA(){
        return A;
    }

    public ArrayList<Integer> getB(){
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
        return costs;
    }

}
