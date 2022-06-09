package com.valkoshkin.model;

import com.valkoshkin.exceptions.DuplicateModelNameException;
import com.valkoshkin.exceptions.NoSuchModelNameException;
import com.valkoshkin.visitor.Visitor;

import java.io.Serializable;

public interface Transport extends Cloneable, Serializable {
    String getBrand();
    void setBrand(String brand);
    int getModelsLength();
    String[] getModelsNames();
    void setModelNameByName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelNameException;
    double getModelPriceByName(String name) throws NoSuchModelNameException;
    void setModelPriceByName(String name, double price) throws NoSuchModelNameException;
    double[] getModelsPrices();
    void addModel(String name, double price) throws DuplicateModelNameException;
    void deleteModel(String name) throws NoSuchModelNameException;
    Transport clone() throws CloneNotSupportedException;
    void accept(Visitor visitor);
    String getSimpleClassName();
}
