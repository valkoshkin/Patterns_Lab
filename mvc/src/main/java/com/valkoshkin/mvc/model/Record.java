package com.valkoshkin.mvc.model;

public class Record {
    private double x;
    private double y;

    public Record(double x) {
        setX(x);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        y = ConcreteFunction.getInstance().calculateY(x);
    }

    public double getY() {
        return y;
    }

}
