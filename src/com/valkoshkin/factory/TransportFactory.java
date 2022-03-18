package com.valkoshkin.factory;

import com.valkoshkin.model.Transport;

public interface TransportFactory {
    Transport createInstance(String brand, int modelsLength);
}
