package org.openjfx;

import java.util.ArrayList;

public class MinimalCostPlan {
    private ArrayList<Integer> A;
    private ArrayList<Integer> B;
    private ArrayList<ArrayList<Integer>> plan = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<Integer>>> solutionPlan = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<Integer>>> solutionMatrix = new ArrayList<>();

    public MinimalCostPlan(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<ArrayList<Integer>> matrixCosts){
        this.A = new ArrayList<>(A); // В конструктор передаётся сначала Элемент А - потребители, потом - B
        this.B = new ArrayList<>(B); // Создаём именно новый элемент, чтобы при изменении this.A не менялся объект A
        int ASum = this.A.stream().mapToInt(k -> k).sum();
        int BSum = this.B.stream().mapToInt(k -> k).sum();

        if (ASum + BSum == 0) throw new RuntimeException("There is nothing to deliver"); // Если нет ничего в A и B

        // Создаём план и заполняем нулями
        ArrayList<ArrayList<Integer>> plan = new ArrayList<>();
        for (int i = 0; i < this.B.size(); i++){
            ArrayList<Integer> toAddToZeroPlan = new ArrayList<>();
            for (int j = 0; j < this.A.size(); j++){
                toAddToZeroPlan.add(0);
            }
            plan.add(toAddToZeroPlan);
        }

        if (ASum > BSum){
            ArrayList<Integer> dop = new ArrayList<>();
            for (int i = 0; i < this.A.size(); i++){
                dop.add(0);
            }
            plan.add(dop);
            matrixCosts.add(dop);
            this.B.add(ASum - BSum);
        }
        if (BSum > ASum){
            // Оказалось, что товара у поставщиков больше, чем надо потребителям
            // Таким образом, мы просто выкидываем избыток товара фиктивному покупателю, которого вводим
            for (ArrayList<Integer> arr: plan){
                arr.add(0);
            }
            for (ArrayList<Integer> arr: matrixCosts){
                arr.add(0);
            }
            this.A.add(BSum - ASum);
        }
        // Копируем все элементы из дано для работы с ними, так как это ссылочный тип данных
        for (ArrayList<Integer> el: plan){
            ArrayList<Integer> dop = new ArrayList<>();
            dop.addAll(el);
            this.plan.add(dop);
        }

        for (ArrayList<Integer> el: matrixCosts){
            ArrayList<Integer> dop = new ArrayList<>();
            dop.addAll(el);
            this.matrix.add(dop);
        }
    }

    public ArrayList<ArrayList<Integer>> getPlan(){
        int min = this.matrix.get(0).get(0);
        int ii = 0;
        int jj = 0;
        int max = 99999;
        // TODO Fix the maximum of the matrix

        while (this.A.stream().mapToInt(k -> k).sum() + this.B.stream().mapToInt(k -> k).sum() > 0) {
            for (int j = 0; j < this.B.size(); j++) {
                for (int i = 0; i < this.A.size(); i++) {
                    if (this.matrix.get(j).get(i) < min) {
                        min = this.matrix.get(j).get(i);
                        ii = i;
                        jj = j;
                    }
                }
            }
            if (this.A.get(ii) <= this.B.get(jj)) {
                this.plan.get(jj).set(ii, this.A.get(ii));
                for (int j = 0; j < this.B.size(); j++) {
                    this.matrix.get(j).set(ii, max + 1);
                }
                this.B.set(jj, this.B.get(jj) - this.A.get(ii));
                this.A.set(ii, 0);
                min = max;
            } else {
                this.plan.get(jj).set(ii, this.B.get(jj));
                for (int i = 0; i < this.A.size(); i++) {
                    this.matrix.get(jj).set(i, max + 1);
                }
                this.A.set(ii, this.A.get(ii) - this.B.get(jj));
                this.B.set(jj, 0);
                min = max;
            }

            ArrayList<ArrayList<Integer>> extra = new ArrayList<>();
            for (ArrayList<Integer> el: this.plan){
                ArrayList<Integer> dop = new ArrayList<>();
                extra.add((ArrayList<Integer>) el.clone());
            }
            this.solutionPlan.add(extra);

            ArrayList<ArrayList<Integer>> extraMatrix = new ArrayList<>();
            for (ArrayList<Integer> el: this.matrix){
                ArrayList<Integer> dop = new ArrayList<>();
                extraMatrix.add((ArrayList<Integer>) el.clone());
            }
            this.solutionMatrix.add(extraMatrix);
        }
        return plan;
    }

    public ArrayList getSolutionPlan() {
        System.out.println(solutionPlan);
        return solutionPlan;
    }

    public ArrayList getSolutionMatrix() {
        System.out.println(solutionMatrix);
        return solutionMatrix;
    }
}
