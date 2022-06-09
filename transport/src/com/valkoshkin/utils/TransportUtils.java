package com.valkoshkin.utils;

import com.valkoshkin.deprecated.factory.CarFactory;
import com.valkoshkin.deprecated.factory.MotorbikeFactory;
import com.valkoshkin.deprecated.factory.TransportFactory;
import com.valkoshkin.model.Car;
import com.valkoshkin.model.Motorbike;
import com.valkoshkin.model.SynchronizedTransport;
import com.valkoshkin.model.Transport;

public class TransportUtils {
    private static TransportFactory transportFactory = new CarFactory();

    public static void setTransportFactory(String className) throws ClassNotFoundException {
        if (className.equals(Car.class.getSimpleName())) {
            transportFactory = new CarFactory();
        } else if (className.equals(Motorbike.class.getSimpleName())) {
            transportFactory = new MotorbikeFactory();
        } else {
            throw new ClassNotFoundException();
        }
    }

    public static void setTransportFactory(TransportFactory transportFactory) {
        TransportUtils.transportFactory = transportFactory;
    }

    public static Transport createInstance(String brand, int modelsLength) {
        return transportFactory.createInstance(brand, modelsLength);
    }

    public static double getAveragePrice(Transport transport) {
        double sum = 0;
        for (double price : transport.getModelsPrices()) {
            sum += price;
        }
        return sum / transport.getModelsLength();
    }

    public static void printModelsNamesWithPrices(Transport transport) {
        String[] modelsNames = transport.getModelsNames();
        double[] modelsPrices = transport.getModelsPrices();
        for (int i = 0; i < transport.getModelsLength(); i++) {
            System.out.printf("%d. %s: %f%n", i + 1, modelsNames[i], modelsPrices[i]);
        }
    }

    public static void printModelsNames(Transport transport) {
        String[] modelsNames = transport.getModelsNames();
        for (int i = 0; i < transport.getModelsLength(); i++) {
            System.out.printf("%d. %s", i + 1, modelsNames[i]);
        }
    }

    public static void printModelsPrices(Transport transport) {
        double[] modelsPrices = transport.getModelsPrices();
        for (int i = 0; i < transport.getModelsLength(); i++) {
            System.out.printf("%d. %f%n", i + 1, modelsPrices[i]);
        }
    }

    public static Transport synchronizedTransport (Transport transport) {
        return new SynchronizedTransport(transport);
    }

    public static String getPreparedRowString (Transport transport) {
        StringBuilder builder = new StringBuilder();
        builder.append("Brand: ").append(transport.getBrand()).append("; Models: [ ");

        String[] modelsNames = transport.getModelsNames();
        double[] modelsPrices = transport.getModelsPrices();
        for (int i = 0; i < transport.getModelsLength(); i++) {
            String formatString = i == transport.getModelsLength() - 1 ? "%d. %s: %f" : "%d. %s: %f; ";
            builder.append(String.format(formatString, i + 1, modelsNames[i], modelsPrices[i]));
        }
        builder.append(" ]\n\n- - - - - -\n\n");

        return builder.toString();
    }

    public static String getPreparedColumnString (Transport transport) {
        StringBuilder builder = new StringBuilder("Simple class name: ")
                .append(transport.getSimpleClassName())
                .append("\nBrand: ")
                .append(transport.getBrand())
                .append("\nNumber of models:\n")
                .append(transport.getModelsLength())
                .append("\nModels:\n");

        String[] modelsNames = transport.getModelsNames();
        double[] modelsPrices = transport.getModelsPrices();
        for (int i = 0; i < transport.getModelsLength(); i++) {
            builder.append(String.format("%d. %s: %f\n", i + 1, modelsNames[i], modelsPrices[i]));
        }
        builder.append("\n- - - - - -\n\n");

        return builder.toString();
    }
}
