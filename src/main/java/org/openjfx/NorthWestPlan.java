package org.openjfx;

import java.util.ArrayList;

public class NorthWestPlan {
    public ArrayList<Integer> A;
    public ArrayList<Integer> B;
    public ArrayList<ArrayList<Integer>> plan = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
    public ArrayList<ArrayList<ArrayList<Integer>>> solutionPlan = new ArrayList<>();
    public ArrayList<ArrayList<ArrayList<Integer>>> solutionMatrix = new ArrayList<>();

    public NorthWestPlan(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<ArrayList<Integer>> matrixCosts) {
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

        for (ArrayList<Integer> el: plan){
            ArrayList<Integer> dop = new ArrayList<>();
            dop.addAll(el);
            this.plan.add(dop);
        }

        // Копируем все элементы из дано для работы с ними, так как это ссылочный тип данных
        for (ArrayList<Integer> el: matrixCosts){
            ArrayList<Integer> dop = new ArrayList<>();
            dop.addAll(el);
            this.matrix.add(dop);
        }
    }

    public ArrayList<ArrayList<Integer>> getPlan(){
        int i = 0;
        int j = 0;

        // В процессе заполнения будем менять необходимые значения потребностей потребителей и поставщиков
        // Заполняем матрицу, пока потребности в продаже поставщиков и потребности покупки не станут нулями
        while (this.A.stream().mapToInt(k -> k).sum() + this.B.stream().mapToInt(k -> k).sum() != 0) {
            if (this.A.get(i) <= this.B.get(j)){ // Если потребность в покупке меньше
                plan.get(j).set(i, this.A.get(i)); // Добавляем значение в план
                this.B.set(j, this.B.get(j) - this.A.get(i)); // Уменьшаем потребность в продаже у постовщика
                this.A.set(i, 0); // Обнуляем потребность в покупке у потребителя
                i++;
            }
            else{
                plan.get(j).set(i, this.B.get(j));
                this.A.set(i, this.A.get(i) - this.B.get(j));
                this.B.set(j, 0);
                j++;
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

    public ArrayList<ArrayList<ArrayList<Integer>>> getSolutionPlan() {
        System.out.println(solutionPlan);
        return solutionPlan;
    }

    public ArrayList<ArrayList<ArrayList<Integer>>> getSolutionMatrix() {
        System.out.println(solutionMatrix);
        return solutionMatrix;
    }
}