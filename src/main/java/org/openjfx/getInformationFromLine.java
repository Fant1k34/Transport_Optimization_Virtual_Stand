package org.openjfx;

import java.util.ArrayList;

public class getInformationFromLine {
    public static ArrayList<ArrayList<Integer>> getInfo(String field){
        ArrayList<ArrayList<Integer>> answer = new ArrayList<>();
        for (String el: field.split("\n")){
            ArrayList<Integer> toAdd = new ArrayList<>();
            for (String il: el.split(" ")){
                try {
                    Integer.valueOf(il);
                }
                catch (Exception e){
                    return null;
                }
                toAdd.add(Integer.valueOf(il));
            }
            answer.add(toAdd);
        }
        return answer;
    }
}
