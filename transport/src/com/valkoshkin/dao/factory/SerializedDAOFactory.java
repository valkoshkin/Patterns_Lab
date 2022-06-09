package com.valkoshkin.dao.factory;

import com.valkoshkin.dao.SerializedTransportDAO;
import com.valkoshkin.dao.TransportDAO;

public class SerializedDAOFactory implements DAOFactory {
    @Override
    public TransportDAO createTransportDAO(String path) {
        return new SerializedTransportDAO(path);
    }
}
