package com.valkoshkin.dao;

import com.valkoshkin.model.Transport;
import com.valkoshkin.utils.TransportUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class TextTransportDAO implements TransportDAO {
    private final String path;
    private final String SEPARATOR = "@@";

    public TextTransportDAO(String path) {
        this.path = path;
    }

    @Override
    public void writeTransport(Transport transport) throws Exception {
        try (var writer = new PrintWriter(new FileWriter(path))) {
            writer.println(transport.getSimpleClassName());
            writer.println(transport.getBrand());

            var modelsLength = transport.getModelsLength();
            writer.println(modelsLength);

            var modelsNames = transport.getModelsNames();
            var modelsPrices = transport.getModelsPrices();
            for (int i = 0; i < modelsLength; i++) {
                writer.println(modelsNames[i] + SEPARATOR + modelsPrices[i]);
            }
        }
    }

    @Override
    public Transport readTransport() throws Exception {
        try (var reader = new BufferedReader(new FileReader(path))) {
            var className = reader.readLine();
            TransportUtils.setTransportFactory(className);

            var brand = reader.readLine();

            var modelsLength = Integer.parseInt(reader.readLine());
            Transport transport = TransportUtils.createInstance(brand, modelsLength);

            for (int i = 0; i < modelsLength; i++) {
                var modelRaw = reader.readLine();
                var splitModel = modelRaw.split(SEPARATOR);
                transport.addModel(splitModel[0], Double.parseDouble(splitModel[1]));
            }
            return transport;

        }
    }
}
