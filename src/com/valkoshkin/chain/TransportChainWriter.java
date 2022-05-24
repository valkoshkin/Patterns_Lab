package com.valkoshkin.chain;

import com.valkoshkin.model.Transport;

import java.io.IOException;

public interface TransportChainWriter {
    void write(Transport transport) throws IOException;
    void setNext (TransportChainWriter next);
}
