package com.valkoshkin.visitor;

import com.valkoshkin.model.Car;
import com.valkoshkin.model.Motorbike;
import com.valkoshkin.utils.TransportUtils;

public class PrintVisitor implements Visitor {
    @Override
    public void visit(Car car) {
        System.out.println(TransportUtils.getPreparedRowString(car));
    }

    @Override
    public void visit(Motorbike motorbike) {
        System.out.println(TransportUtils.getPreparedColumnString(motorbike));
    }
}
