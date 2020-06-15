package org.openjfx;

import java.util.ArrayList;
import java.util.Arrays;
/*
This class let you get A - buyers, B - providers and the costs matrix
 */

public class Data {
    private ArrayList<Integer> A = new ArrayList<>();
    private ArrayList<Integer> B = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> costs = new ArrayList<>();

    public Data(ArrayList<ArrayList<Integer>> StartPlan, ArrayList<ArrayList<Integer>> StartCosts){
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

    public ArrayList<ArrayList<Integer>> getCosts(){
        return costs;
    }

}
