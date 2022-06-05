package com.valkoshkin.model;

public class AverageMark {
    private final double currentAverage;
    private final double correctAverage;

    public AverageMark(double currentAverage, double correctAverage) {
        this.currentAverage = currentAverage;
        this.correctAverage = correctAverage;
    }

    public double getCurrentAverage() {
        return currentAverage;
    }

    public double getCorrectAverage() {
        return correctAverage;
    }
}
