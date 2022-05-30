package com.valkoshkin.chain;

import com.valkoshkin.model.Transport;
import com.valkoshkin.utils.TransportUtils;

import java.io.FileWriter;
import java.io.IOException;

public class TransportChainWriterColumn implements TransportChainWriter {
    private TransportChainWriter next;
    private final FileWriter writer;

    public TransportChainWriterColumn(FileWriter writer) {
        this.writer = writer;
    }

    @Override
    public void write(Transport transport) throws IOException {
        if (transport.getModelsLength() > 3) {
            writer.write(TransportUtils.getPreparedColumnString(transport));
        } else if (next != null) {
            next.write(transport);
        }
    }

    @Override
    public void setNext(TransportChainWriter next) {
        this.next = next;
    }
}
