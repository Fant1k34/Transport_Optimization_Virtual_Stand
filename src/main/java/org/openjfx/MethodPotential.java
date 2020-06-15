package org.openjfx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class MethodPotential {
    private ArrayList<ArrayList<Integer>> plan = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
    private ArrayList<Integer> U = new ArrayList<>();
    private ArrayList<Integer> V = new ArrayList<>();
    private ArrayList<String> steps = new ArrayList<>();
    private ArrayList<String> Usteps = new ArrayList<>();
    private ArrayList<String> Vsteps = new ArrayList<>();
    private Integer startValue = -1;
    private Integer finishValue = -1;

    public ArrayList<ArrayList<Integer>> getPlan() {
        return plan;
    }

    public ArrayList<ArrayList<Integer>> getMatrix() {
        return matrix;
    }

    public ArrayList<Integer> getU() {
        return U;
    }

    public ArrayList<Integer> getV() {
        return V;
    }

    public MethodPotential(ArrayList<ArrayList<Integer>> primaryPlan, ArrayList<ArrayList<Integer>> primaryMatrix){
        // Подготавливаем элементы, с которыми будем работать
        for (ArrayList<Integer> el: primaryPlan){
            ArrayList<Integer> dop = new ArrayList<>();
            dop.addAll(el);
            this.plan.add(dop);
        }
        for (ArrayList<Integer> el: primaryMatrix){
            ArrayList<Integer> dop = new ArrayList<>();
            dop.addAll(el);
            this.matrix.add(dop);
        }
    }

    // Прошлый метод для подсчёта потенциалов
    public boolean tryToCountThePotential(){
        int uSt = 0;
        int vSt = 0;
        for (int k = 0; k < V.size() * U.size() + 10; k++) {
            for (int ii = uSt; ii < U.size() + uSt; ii++) {
                for (int jj = vSt; jj < V.size() + vSt; jj++) {
                    int i = ii % U.size();
                    int j = jj % V.size();
                    if (V.get(j) == null || this.plan.get(i).get(j) > 0) {
                        try {
                            int znach = this.matrix.get(i).get(j) - U.get(i);
                            V.set(j, znach);
                        } catch (Exception e) {
                            // Do nothing
                        }
                    }
                    if (U.get(i) == null || this.plan.get(i).get(j) > 0) {
                        try {
                            int znach = this.matrix.get(i).get(j) - V.get(j);
                            U.set(i, znach);
                        } catch (Exception e) {
                            // Do nothing
                        }
                    }
                }
            }
        }
        return true;
    }


    private boolean tryToFindPotentialsByMyNewOptimizedMethod(){
        ArrayList<String> attempt = new ArrayList<>();
        ArrayList<String> foundU = new ArrayList<>();
        ArrayList<String> foundV = new ArrayList<>();

        // Пробегаемся по U0 -> ищем все столбцы, потенциалы которых мы можем найти
        // Считаем их, и добавляем в attempt -> возможные для подсчёта строки или столбцы

        attempt.add("U0");
        while (attempt.size() > 0){
            // Получаем первой буквой U -> строка
            if (attempt.get(0).subSequence(0, 1).equals("U")){
                int i = Integer.parseInt((String) attempt.get(0).subSequence(1, 2));
                // Находим ненулевые элементы без повторений
                for (int j = 0; j < V.size(); j++){
                    if (this.plan.get(i).get(j) > 0 && !(foundU.contains("V" + j))){
                        attempt.add("V" + String.valueOf(j));
                        V.set(j, this.matrix.get(i).get(j) - U.get(i));
                        foundV.add("V" + String.valueOf(j));
                    }
                }
            }
            // Получаем второй буквой V -> столбец
            else {
                int j = Integer.parseInt((String) attempt.get(0).subSequence(1, 2));
                for (int i = 0; i < U.size(); i++){
                    // Находим ненулевые элементы без повторений
                    if (this.plan.get(i).get(j) > 0 && !(foundU.contains("U" + i))){
                        attempt.add("U" + String.valueOf(i));
                        U.set(i, this.matrix.get(i).get(j) - V.get(j));
                        foundU.add("U" + String.valueOf(i));
                    }
                }
            }
            attempt.remove(0);
        }

        // Проверяем, получилось ли расставить потенциалы

        try {
            U.stream().mapToInt(i -> i).reduce((r, l) -> r * l).getAsInt();
            V.stream().mapToInt(i -> i).reduce((r, l) -> r * l).getAsInt();
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    private ArrayList<Integer> findMax(){
        // Метод позволяет найти максимальное отклонение, если план не оптимален!!!
        int imax = -1;
        int jmax = -1;
        int z = 0;
        for (int i = 0; i < U.size(); i++){
            for (int j = 0; j < V.size(); j++){
                if (U.get(i) + V.get(j) - matrix.get(i).get(j) > z){
                    z = U.get(i) + V.get(j) - matrix.get(i).get(j);
                    imax = i;
                    jmax = j;
                }
            }
        }
        if (imax + jmax < 0) throw new RuntimeException("ПЛАН УЖЕ ОПТИМАЛЕН!!!");
        return new ArrayList<>(Arrays.asList(imax, jmax));
    }


    private boolean ifCircle(ArrayList<String> used){
        // Длина цикла >= 4
        if (used.size() <= 3) return false;
        // Цикл начинается с x1 и заканцивается x1, Соответсвенно HashSet != ArrayList
        if (new HashSet<String>(used).size() == used.size()) return false;
        // HashSet != ArrayList, если мы берём все, кроме последнего элемента! Важна уникальность внутренних
        if (new HashSet<String>(used.subList(0, used.size() - 2)).size() != used.subList(0, used.size() - 2).size()) return false;
        // Первый и последний элементы должны быть равны
        if (!used.get(0).equals(used.get(used.size() - 1))) return false;
        return true;
    }

    private boolean ifOK(ArrayList<String> used){
        if (used.size() == 2 && used.get(1).equals(startPointU)) return false;
        if (used.size() <= 1) return true;
        return !used.subList(1, used.size() - 1).contains(used.get(used.size() - 1));
    }



    private void extend(){
        if (used.get(used.size() - 1).subSequence(0, 1).equals("V")) {
            int z = Integer.parseInt(String.valueOf(used.get(used.size() - 1).subSequence(1, 2)));
            // Теперь z - индекс
            for (int i = 0; i < matrix.size(); i++){
                if (plan.get(i).get(z) > 0 && ifOK(used)) {
                    used.add("U" + i);
                    if (ifCircle(used)){
                        answer.clear();
                        answer.addAll(used);
                        used.remove(used.size() - 1);
                        return;
                    }
                    extend();
                    used.remove(used.size() - 1);
                }
            }
            return;
        }
        if (used.get(used.size() - 1).subSequence(0, 1).equals("U")) {
            int z = Integer.parseInt(String.valueOf(used.get(used.size() - 1).subSequence(1, 2)));
            // Теперь z - индекс
            for (int j = 0; j < matrix.get(0).size(); j++){
                if (plan.get(z).get(j) > 0 && ifOK(used)){
                    used.add("V" + j);
                    if (ifCircle(used)){
                        answer.clear();
                        answer.addAll(used);
                        used.remove(used.size() - 1);
                        return;
                    }
                    extend();
                    used.remove(used.size() - 1);
                }
            }
            return;
        }
    }
    private ArrayList<String> answer = new ArrayList<>();
    private ArrayList<String> used = new ArrayList<>();
    private String startPointU;
    private String startPointV;

    private boolean findCircle(){
        // Метод позволяет найти контур
        int ii, jj;
        ii = findMax().get(0);
        jj = findMax().get(1);
        startPointU = "U" + ii;
        startPointV = "V" + jj;

        plan.get(ii).set(jj, 1);
        // НАЧИНАЕМ ВСЕГДА С startPointV !!!!!!!!!!
        used.add(startPointV);
        extend();
        used.clear();
        plan.get(ii).set(jj, 0);
        System.out.println(answer);
        return true;
    }


    private int countCostsForPlan(){
        int sum = 0;
        for (int i = 0; i < plan.size(); i++){
            for (int j = 0; j < plan.get(0).size(); j++){
                sum += plan.get(i).get(j) * matrix.get(i).get(j);
            }
        }
        return sum;
    }


    public ArrayList<String> getSteps() {
        return steps;
    }

    public ArrayList<String> getUsteps() {
        return Usteps;
    }

    public ArrayList<String> getVsteps() {
        return Vsteps;
    }

    public Integer getStartValue() {
        return startValue;
    }

    public Integer getFinishValue() {
        return finishValue;
    }

    public ArrayList<ArrayList<Integer>> getOptimizedSolution(){
        // Сейчас посчитаем стоимость перевозок для неоптимизированного плана
        int startCosts = countCostsForPlan();

        U.add(0);
        for (int i = 0; i < this.plan.size() - 1; i++){
            U.add(null);
        }

        for (int i = 0; i < this.plan.get(0).size(); i++){
            V.add(null);
        }

        if (!tryToFindPotentialsByMyNewOptimizedMethod()) System.out.println("Невозможно расставить потенциалы!!!");

        tryToFindPotentialsByMyNewOptimizedMethod();
        while (!this.isOptimized(U, V)){
            findCircle();
            ArrayList<Integer> x = new ArrayList<>();
            ArrayList<Integer> y = new ArrayList<>();
            System.out.println("Потенциалы: ");
            System.out.println(U);
            System.out.println(V);
            this.Usteps.add(U.toString());
            this.Vsteps.add(V.toString());

            for (int i = 0; i < answer.size() - 1; i++){
                String el = answer.get(i);
                String il = answer.get(i + 1);
                if (el.subSequence(0, 1).equals("V")){
                    x.add(Integer.parseInt(String.valueOf(el.subSequence(1, 2))));
                    y.add(Integer.parseInt(String.valueOf(il.subSequence(1, 2))));
                }
                else {
                    y.add(Integer.parseInt(String.valueOf(el.subSequence(1, 2))));
                    x.add(Integer.parseInt(String.valueOf(il.subSequence(1, 2))));

                }
            }

            int minimum = plan.get(y.get(0)).get(x.get(0));
            // Найдём минимальный i-чётное значение plan
            for (int i = 0; i < x.size(); i++){
                if (i % 2 == 0){
                    if (plan.get(y.get(i)).get(x.get(i)) < minimum){
                        minimum = plan.get(y.get(i)).get(x.get(i));
                    }
                }
            }
            // Выполняем перераспределение поставок(товаров)
            System.out.println("--- (До перераспределения)");
            System.out.println(plan);
            for (int i = 0; i < x.size(); i++){
                if (i % 2 == 0){
                    plan.get(y.get(i)).set(x.get(i), plan.get(y.get(i)).get(x.get(i)) - minimum);
                }
                else {
                    plan.get(y.get(i)).set(x.get(i), plan.get(y.get(i)).get(x.get(i)) + minimum);
                }
                System.out.println(plan);
            }

            String toAddtosteps = "";
            for (ArrayList el: plan){
                toAddtosteps += el.toString();
                toAddtosteps += "\n";
            }
            this.steps.add(toAddtosteps);

            if (!tryToFindPotentialsByMyNewOptimizedMethod()) break;
        }

        // Посчитаем стоимости после перевозок
        int finalCosts = countCostsForPlan();

        System.out.println("Оптимизированный план");
        System.out.println(plan);
        System.out.println("Итоговые потенциалы");
        System.out.println(U);
        System.out.println(V);
        System.out.println("Начальная стоимость - " + startCosts);
        System.out.println("Конечная стоимость - " + finalCosts);
        this.Usteps.add(U.toString());
        this.Vsteps.add(V.toString());
        this.startValue = startCosts;
        this.finishValue = finalCosts;
        return null;
    }



    private boolean isOptimized(ArrayList<Integer> U, ArrayList<Integer> V){
        for (int i = 0; i < U.size(); i++){
            for (int j = 0; j < V.size(); j++){
                if (U.get(i) + V.get(j) > this.matrix.get(i).get(j) && this.plan.get(i).get(j) == 0) {
                    return false;
                }
            }
        }
        return true;
    }


}
