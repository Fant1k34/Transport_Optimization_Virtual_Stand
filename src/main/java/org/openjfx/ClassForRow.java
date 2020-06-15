package org.openjfx;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class ClassForRow {
    public SimpleStringProperty V = null;
    public SimpleStringProperty V0 = null;
    public SimpleStringProperty V1 = null;
    public SimpleStringProperty V2 = null;

    public String getV() {
        return V.get();
    }

    public SimpleStringProperty vProperty() {
        return V;
    }

    public void setV(String v) {
        this.V.set(v);
    }

    public String getV0() {
        return V0.get();
    }

    public SimpleStringProperty v0Property() {
        return V0;
    }

    public void setV0(String v0) {
        this.V0.set(v0);
    }

    public String getV1() {
        return V1.get();
    }

    public SimpleStringProperty v1Property() {
        return V1;
    }

    public void setV1(String v1) {
        this.V1.set(v1);
    }

    public String getV2() {
        return V2.get();
    }

    public SimpleStringProperty v2Property() {
        return V2;
    }

    public void setV2(String v2) {
        this.V2.set(v2);
    }

    public String getV3() {
        return V3.get();
    }

    public SimpleStringProperty v3Property() {
        return V3;
    }

    public void setV3(String v3) {
        this.V3.set(v3);
    }

    public String getV4() {
        return V4.get();
    }

    public SimpleStringProperty v4Property() {
        return V4;
    }

    public void setV4(String v4) {
        this.V4.set(v4);
    }

    public String getV5() {
        return V5.get();
    }

    public SimpleStringProperty v5Property() {
        return V5;
    }

    public void setV5(String v5) {
        this.V5.set(v5);
    }

    public String getV6() {
        return V6.get();
    }

    public SimpleStringProperty v6Property() {
        return V6;
    }

    public void setV6(String v6) {
        this.V6.set(v6);
    }

    public String getV7() {
        return V7.get();
    }

    public SimpleStringProperty v7Property() {
        return V7;
    }

    public void setV7(String v7) {
        this.V7.set(v7);
    }

    public String getV8() {
        return V8.get();
    }

    public SimpleStringProperty v8Property() {
        return V8;
    }

    public void setV8(String v8) {
        this.V8.set(v8);
    }

    public String getV9() {
        return V9.get();
    }

    public SimpleStringProperty v9Property() {
        return V9;
    }

    public void setV9(String v9) {
        this.V9.set(v9);
    }

    private SimpleStringProperty V3 = null;
    private SimpleStringProperty V4 = null;
    private SimpleStringProperty V5 = null;
    private SimpleStringProperty V6 = null;
    private SimpleStringProperty V7 = null;
    private SimpleStringProperty V8 = null;
    private SimpleStringProperty V9 = null;

    public ClassForRow(ArrayList<Integer> array, int number){
        V = new SimpleStringProperty(String.valueOf(number));
        try {
            V0 = new SimpleStringProperty(String.valueOf(array.get(0)));
            V1 = new SimpleStringProperty(String.valueOf(array.get(1)));
            V2 = new SimpleStringProperty(String.valueOf(array.get(2)));
            V3 = new SimpleStringProperty(String.valueOf(array.get(3)));
            V4 = new SimpleStringProperty(String.valueOf(array.get(4)));
            V5 = new SimpleStringProperty(String.valueOf(array.get(5)));
            V6 = new SimpleStringProperty(String.valueOf(array.get(6)));
            V7 = new SimpleStringProperty(String.valueOf(array.get(7)));
            V8 = new SimpleStringProperty(String.valueOf(array.get(8)));
            V9 = new SimpleStringProperty(String.valueOf(array.get(9)));
        }
        catch (Exception e){

        }
    }
}
