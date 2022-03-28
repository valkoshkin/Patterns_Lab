package com.valkoshkin;

import com.valkoshkin.exceptions.DuplicateModelNameException;
import com.valkoshkin.exceptions.NoSuchModelNameException;
import com.valkoshkin.factory.MotorbikeFactory;
import com.valkoshkin.model.Transport;
import com.valkoshkin.singleton.Config;
import com.valkoshkin.utils.TransportUtils;

public class Main {

    public static void main(String[] args) {
        testSingleton();
        System.out.println();
        testFactoryWithPrototype();
    }

    public static void testSingleton() {
        var properties = Config.getInstance().getConfig();
        System.out.println("Subject: " + properties.getProperty("subject"));
        System.out.println("Language: " + properties.getProperty("language"));
    }

    public static void testFactoryWithPrototype() {
        try {
            Transport car = TransportUtils.createInstance("BMW", 4);
            addCarModels(car);
            System.out.println(car.getClass());

            TransportUtils.setTransportFactory(new MotorbikeFactory());
            Transport motorbike = TransportUtils.createInstance("Yamaha", 4);
            addMotorbikeModels(motorbike);
            System.out.println(motorbike.getClass());


            Transport carClone = car.clone();
            carClone.setModelNameByName("X5", "NEW X5");
            carClone.setModelPriceByName("C12", 999.99);
            Transport motorbikeClone = motorbike.clone();
            motorbikeClone.setModelNameByName("M-12", "NEW M-12");
            motorbikeClone.setModelPriceByName("T-41", 999.99);

            System.out.println("\n[Car original]");
            printTransport(car);
            System.out.println("\n[Car cloned]");
            printTransport(carClone);

            System.out.println("\n[Motorbike original]");
            printTransport(motorbike);
            System.out.println("\n[Motorbike cloned]");
            printTransport(motorbikeClone);

        } catch (DuplicateModelNameException | CloneNotSupportedException | NoSuchModelNameException e) {
            e.printStackTrace();
        }
    }

    public static void addCarModels(Transport transport) throws DuplicateModelNameException {
        transport.addModel("X5", 123);
        transport.addModel("X6", 834);
        transport.addModel("A3", 172.99);
        transport.addModel("C12", 534.12);
    }

    public static void addMotorbikeModels(Transport transport) throws DuplicateModelNameException {
        transport.addModel("M-12", 104.33);
        transport.addModel("R-8", 542);
        transport.addModel("ZX-2", 712);
        transport.addModel("T-41", 404);
    }

    public static void printTransport(Transport transport) {
        System.out.println("Brand: " + transport.getBrand());
        System.out.println("Models length: " + transport.getModelsLength());
        System.out.println("Models:");
        TransportUtils.printModelsNamesWithPrices(transport);
    }
}
