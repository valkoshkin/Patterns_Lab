package com.valkoshkin.dao;

import com.valkoshkin.model.Transport;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializedTransportDAO implements TransportDAO {
    private final String path;

    public SerializedTransportDAO(String path) {
        this.path = path;
    }

    @Override
    public void writeTransport(Transport transport) throws Exception {
        try (var stream = new ObjectOutputStream(new FileOutputStream(path))) {
            stream.writeObject(transport);
        }
    }

    @Override
    public Transport readTransport() throws Exception {
        try (var stream = new ObjectInputStream(new FileInputStream(path))) {
            return (Transport) stream.readObject();
        }
    }
}
