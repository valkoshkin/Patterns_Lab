package com.valkoshkin.mvc.model;

public class ConcreteFunction implements Function {
    private static Function instance;

    private ConcreteFunction() {
    }

    public static Function getInstance() {
        if (instance == null) {
            instance = new ConcreteFunction();
        }
        return instance;
    }

    @Override
    public double calculateY(double x) {
        return (0.2 * Math.pow(x, 3) + 4 * Math.pow(x, 2) - 12 * x) / 12;
    }
}
