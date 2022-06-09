package com.valkoshkin.dao.factory;

import com.valkoshkin.dao.TextTransportDAO;
import com.valkoshkin.dao.TransportDAO;

public class TextDAOFactory implements DAOFactory {
    @Override
    public TransportDAO createTransportDAO(String path) {
        return new TextTransportDAO(path);
    }
}
