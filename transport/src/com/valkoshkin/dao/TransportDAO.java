package com.valkoshkin.dao;

import com.valkoshkin.model.Transport;

public interface TransportDAO {
    void writeTransport(Transport transport) throws Exception;
    Transport readTransport() throws Exception;
}
