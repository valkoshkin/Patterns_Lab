package com.valkoshkin.utils;

import com.valkoshkin.factory.CarFactory;
import com.valkoshkin.factory.TransportFactory;
import com.valkoshkin.model.SynchronizedTransport;
import com.valkoshkin.model.Transport;

public class TransportUtils {
    private static TransportFactory transportFactory = new CarFactory();

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
}
