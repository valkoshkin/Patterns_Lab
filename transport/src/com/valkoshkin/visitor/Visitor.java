package com.valkoshkin.visitor;

import com.valkoshkin.model.Car;
import com.valkoshkin.model.Motorbike;

public interface Visitor {
    void visit(Car car);
    void visit(Motorbike motorbike);
}
