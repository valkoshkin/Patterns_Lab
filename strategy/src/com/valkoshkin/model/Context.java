package com.valkoshkin.model;

import com.valkoshkin.model.strategy.Strategy;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void checkAndFixAverageMark(String outputFilePath) {
        try {
            var averageMark = strategy.getAverageMark();
            if (averageMark == null) {
                System.out.println("Average mark object is null.");
                return;
            }
            var correctAverage = averageMark.getCorrectAverage();
            var currentAverage = averageMark.getCurrentAverage();

            System.out.println("Correct average mark: " + correctAverage +
                    "\nAverage mark from XML file: " + currentAverage);

            if (currentAverage != correctAverage) {
                strategy.fixAverageMark(correctAverage, outputFilePath);
                System.out.println("New XML document with correct average mark value has been created.");
            } else {
                Files.deleteIfExists(Paths.get(outputFilePath));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
