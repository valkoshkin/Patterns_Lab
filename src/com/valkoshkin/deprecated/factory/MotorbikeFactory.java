package com.valkoshkin.deprecated.factory;

import com.valkoshkin.model.Motorbike;
import com.valkoshkin.model.Transport;

public class MotorbikeFactory implements TransportFactory {
    @Override
    public Transport createInstance(String brand, int modelsLength) {
        return new Motorbike(brand, modelsLength);
    }
}
