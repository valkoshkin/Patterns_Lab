package com.valkoshkin;

import com.valkoshkin.adapter.OutputAdapter;
import com.valkoshkin.adapter.OutputStreamAdapter;
import com.valkoshkin.exceptions.DuplicateModelNameException;
import com.valkoshkin.model.Transport;
import com.valkoshkin.utils.TransportUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Main {

    private static final String[] items = {"first", "second", "third"};
    private static final String FILE_PATH = "result.txt";

    public static void main(String[] args) {
        try (var outputStream = new FileOutputStream(FILE_PATH);
             var inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_PATH)))) {
            OutputAdapter adapter = new OutputStreamAdapter(outputStream);
            adapter.write(items);

            while (inputStream.ready()) {
                System.out.println(inputStream.readLine());
            }
        } catch (Exception e) {
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
