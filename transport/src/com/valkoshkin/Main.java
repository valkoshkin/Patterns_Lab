package com.valkoshkin;

import com.valkoshkin.chain.TransportChainWriter;
import com.valkoshkin.chain.TransportChainWriterColumn;
import com.valkoshkin.chain.TransportChainWriterRow;
import com.valkoshkin.command.PrintCarCommandColumn;
import com.valkoshkin.command.PrintCarCommandRow;
import com.valkoshkin.dao.TransportDAO;
import com.valkoshkin.dao.factory.SerializedDAOFactory;
import com.valkoshkin.dao.factory.TextDAOFactory;
import com.valkoshkin.deprecated.factory.CarFactory;
import com.valkoshkin.deprecated.factory.MotorbikeFactory;
import com.valkoshkin.exceptions.DuplicateModelNameException;
import com.valkoshkin.model.Car;
import com.valkoshkin.model.Motorbike;
import com.valkoshkin.model.Transport;
import com.valkoshkin.utils.TransportUtils;
import com.valkoshkin.visitor.PrintVisitor;
import com.valkoshkin.visitor.Visitor;

import java.io.FileWriter;

public class Main {

    private static final String FILE_PATH = "transport/result.txt";

    public static void main(String[] args) {
//        testChainOfResponsibility();
//        testCommand();
//        testIterator();
//        testMemento();
//        testVisitor();
        testDAO();
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

    public static void testCommand() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Car car = new Car("BMW", 2);
            addCarModels(car);

            car.setPrintCommand(new PrintCarCommandRow(car));
            car.print(writer);

            car.setPrintCommand(new PrintCarCommandColumn(car));
            car.print(writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testIterator() {
        try {
            IterableCar car = new IterableCar("BMW", 2);
            addIterableCarModels(car);
            for (var model : car) {
                System.out.println(model.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testMemento() {
        try {
            MementoCar car = new MementoCar("TESTbrand", 2);
            addCarModels(car);

            var modelNameToEdit = "MODEL";
            car.addModel(modelNameToEdit, 222);

            System.out.println("Source car:");
            TransportUtils.printModelsNamesWithPrices(car);

            var memento = car.createMemento();
            car.setModelPriceByName(modelNameToEdit, 555);
            car.addModel("new model", 987);

            System.out.println("\nModified car:");
            TransportUtils.printModelsNamesWithPrices(car);

            car.setMemento(memento);
            System.out.println("\nRestored source car:");
            TransportUtils.printModelsNamesWithPrices(car);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testVisitor() {
        try {
            Transport car = new Car("BMW", 2);
            Transport bike = new Motorbike("Yamaha", 5);
            addCarModels(car);
            addMotorbikeModels(bike);

            Visitor visitor = new PrintVisitor();

            System.out.println("Print car (row):\n");
            car.accept(visitor);
            System.out.println("Print motorbike (column):\n");
            bike.accept(visitor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testDAO() {
        try {
            String textFilePath = "transport/text-transport.txt";
            String serializedFilePath = "transport/serialized-transport.b";

            var textDAO = new TextDAOFactory().createTransportDAO(textFilePath);
            var serializedDAO = new SerializedDAOFactory().createTransportDAO(serializedFilePath);

            System.out.println("Creating files...\n");
            createFilesForDAO(textDAO, serializedDAO);

            var firstTransport = textDAO.readTransport();
            var secondTransport = serializedDAO.readTransport();

            System.out.println("\nTransport from text file:\n");
            System.out.println(TransportUtils.getPreparedColumnString(firstTransport));

            System.out.println("Deserealized transport:\n");
            System.out.println(TransportUtils.getPreparedColumnString(secondTransport));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void createFilesForDAO(TransportDAO textDAO, TransportDAO serializedDAO) throws Exception {
        Transport car = new Car("BMW", 2);
        addCarModels(car);
        Transport bike = new Motorbike("Yamaha", 5);
        addMotorbikeModels(bike);

        textDAO.writeTransport(car);
        serializedDAO.writeTransport(bike);
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

    public static void addIterableCarModels(IterableCar car) throws DuplicateModelNameException {
        car.addModel("X5", 123);
        car.addModel("X6", 834);
        car.addModel("A3", 172.99);
        car.addModel("C12", 534.12);
    }
}
