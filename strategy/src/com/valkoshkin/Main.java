package com.valkoshkin;

import com.valkoshkin.model.Context;
import com.valkoshkin.model.strategy.DOMStrategy;
import com.valkoshkin.model.strategy.SAXStrategy;

public class Main {
    public static void main (String[] args) {
        if (args.length < 2) {
            System.out.println("Arguments specified incorrectly.");
            return;
        }
        var inputFilePath = args[0];
        var domOutputFilePath = args[1].replace(".xml", "-dom.xml");
        var saxOutputFilePath = args[1].replace(".xml", "-sax.xml");

        var domStrategy = new DOMStrategy(inputFilePath);
        var saxStrategy = new SAXStrategy(inputFilePath);

        System.out.println("DOM");
        var context = new Context(domStrategy);
        context.checkAndFixAverageMark(domOutputFilePath);


        System.out.println("\nSAX");
        context.setStrategy(saxStrategy);
        context.checkAndFixAverageMark(saxOutputFilePath);
    }
}
