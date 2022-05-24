package com.valkoshkin;

import com.valkoshkin.chain.TransportChainWriter;
import com.valkoshkin.chain.TransportChainWriterColumn;
import com.valkoshkin.chain.TransportChainWriterRow;
import com.valkoshkin.exceptions.DuplicateModelNameException;
import com.valkoshkin.model.Car;
import com.valkoshkin.model.Motorbike;
import com.valkoshkin.model.Transport;

import java.io.FileWriter;

public class Main {

    private static final String FILE_PATH = "result.txt";

    public static void main(String[] args) {
        testChainOfResponsibility();
    }

    public static void testChainOfResponsibility() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Transport car = new Car("BMW", 2);
            Transport bike = new Motorbike("Yamaha", 5);
            addCarModels(car);
            addMotorbikeModels(bike);

            TransportChainWriter chainWriter = new TransportChainWriterRow(writer);
            chainWriter.setNext(new TransportChainWriterColumn(writer));
            chainWriter.write(car);
            chainWriter.write(bike);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addCarModels(Transport transport) throws DuplicateModelNameException {
        transport.addModel("X5", 123);
        transport.addModel("X6", 834);
//        transport.addModel("A3", 172.99);
//        transport.addModel("C12", 534.12);
    }

    public static void addMotorbikeModels(Transport transport) throws DuplicateModelNameException {
        transport.addModel("M-12", 104.33);
        transport.addModel("R-8", 542);
        transport.addModel("ZX-2", 712);
        transport.addModel("T-41", 404);
        transport.addModel("RT-10", 670);
    }
}
