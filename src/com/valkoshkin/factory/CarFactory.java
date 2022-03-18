package com.valkoshkin.factory;

import com.valkoshkin.model.Car;
import com.valkoshkin.model.Transport;

public class CarFactory implements TransportFactory {
    @Override
    public Transport createInstance(String brand, int modelsLength) {
        return new Car(brand, modelsLength);
    }
}
