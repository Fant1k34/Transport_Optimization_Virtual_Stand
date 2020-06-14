package org.openjfx;

import java.util.ArrayList;
import java.util.Arrays;

public class getInformationFromLine {
    public static ArrayList<ArrayList<Integer>> getInfo(String field) {
        ArrayList<ArrayList<Integer>> answer = new ArrayList<>();
        for (String el : field.split("\n")) {
            ArrayList<Integer> toAdd = new ArrayList<>();
            for (String il : el.split(" ")) {
                try {
                    Integer.valueOf(il);
                } catch (Exception e) {
                    return null;
                }
                toAdd.add(Integer.valueOf(il));
            }
            answer.add(toAdd);
        }
        return answer;
    }

    public static ArrayList<Integer> getLine(String field) {
        ArrayList<Integer> answer = new ArrayList<>();
        Arrays.stream(field.split(" ")).mapToInt((i -> (int) Integer.valueOf(i))).forEach(answer::add);
        return answer;
    }

}
