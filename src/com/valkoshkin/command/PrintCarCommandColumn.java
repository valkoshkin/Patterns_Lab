package com.valkoshkin.command;

import com.valkoshkin.model.Car;
import com.valkoshkin.utils.TransportUtils;

import java.io.IOException;

public class PrintCarCommandColumn implements PrintCarCommand {
    private final Car car;

    public PrintCarCommandColumn(Car car) {
        this.car = car;
    }

    @Override
    public void execute() throws IOException {
        car.getWriter().write(TransportUtils.getPreparedColumnString(car));
    }
}
