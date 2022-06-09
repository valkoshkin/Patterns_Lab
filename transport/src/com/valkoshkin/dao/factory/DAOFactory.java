package com.valkoshkin.dao.factory;

import com.valkoshkin.dao.TransportDAO;

public interface DAOFactory {
    TransportDAO createTransportDAO(String path);
}
