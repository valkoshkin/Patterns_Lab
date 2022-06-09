package com.valkoshkin.model;

import com.valkoshkin.exceptions.DuplicateModelNameException;
import com.valkoshkin.exceptions.ModelPriceOutOfBoundsException;
import com.valkoshkin.exceptions.NoSuchModelNameException;
import com.valkoshkin.visitor.Visitor;

import java.io.Serializable;

public class Motorbike implements Transport {
    private String brand;
    private int length;
    private Model head = new Model();

    {
        head.prev = head;
        head.next = head;
    }

    public Motorbike(String brand, int modelsLength) {
        this.brand = brand;
        this.length = modelsLength;
    }

    private int getExistedModelsLength() {
        int length = 0;
        Model current = head.next;
        while (current != head) {
            length++;
            current = current.next;
        }
        return length;
    }

    private boolean isModelWithNameExist(String name) {
        Model current = head.next;
        while (current != head) {
            if (current.name.equals(name)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public int getModelsLength() {
        return getExistedModelsLength();
    }

    @Override
    public String[] getModelsNames() {
        String[] modelsNames = new String[length];
        Model current = head.next;
        int index = 0;
        while (current != head) {
            modelsNames[index] = current.name;
            current = current.next;
            index++;
        }
        return modelsNames;
    }

    @Override
    public void setModelNameByName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        if (isModelWithNameExist(newName)) {
            throw new DuplicateModelNameException(String.format("Model with name '%s' already exist.", newName));
        }
        Model current = head.next;
        while (current != head) {
            if (current.name.equals(oldName)) {
                current.name = newName;
                return;
            }
            current = current.next;
        }
        throw new NoSuchModelNameException(String.format("Model with name '%s' not found.", oldName));
    }

    @Override
    public double getModelPriceByName(String name) throws NoSuchModelNameException {
        Model current = head.next;
        while (current != head) {
            if (current.name.equals(name)) {
                return current.price;
            }
            current = current.next;
        }
        throw new NoSuchModelNameException(String.format("Model with name '%s' not found.", name));
    }

    @Override
    public void setModelPriceByName(String name, double price) throws NoSuchModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("The price must be a positive number.");
        }
        Model current = head.next;
        while (current != head) {
            if (current.name.equals(name)) {
                current.price = price;
                return;
            }
            current = current.next;
        }
        throw new NoSuchModelNameException(String.format("Model with name '%s' not found.", name));
    }

    @Override
    public double[] getModelsPrices() {
        double[] modelsPrices = new double[length];
        Model current = head.next;
        int index = 0;
        while (current != head) {
            modelsPrices[index] = current.price;
            current = current.next;
            index++;
        }
        return modelsPrices;
    }

    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("The price must be a positive number.");
        }
        if (isModelWithNameExist(name)) {
            throw new DuplicateModelNameException(String.format("Model with name '%s' already exist.", name));
        }
        Model newModel = new Model(name, price);
        Model lastModel = head.prev;

        lastModel.next = newModel;
        head.prev = newModel;
        newModel.prev = lastModel;
        newModel.next = head;

        if (getExistedModelsLength() > length) {
            length++;
        }
    }

    @Override
    public void deleteModel(String name) throws NoSuchModelNameException {
        Model current = head.next;
        while (current != head) {
            if (current.name.equals(name)) {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                length--;
                return;
            }
            current = current.next;
        }
        throw new NoSuchModelNameException(String.format("Model with name '%s' not found.", name));
    }

    @Override
    public Motorbike clone() throws CloneNotSupportedException {
        var clone = (Motorbike) super.clone();
        clone.head = head.clone();
        clone.head.next = clone.head;
        clone.head.prev = clone.head;

        Model current = head;
        while (current.next != head) {
            try {
                clone.addModel(current.next.name, current.next.price);
            } catch (DuplicateModelNameException e) {
                e.printStackTrace();
            }
            current = current.next;
        }

        return clone;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getSimpleClassName() {
        return Motorbike.class.getSimpleName();
    }

    private class Model implements Cloneable, Serializable {
        String name = null;
        double price = Double.NaN;
        Model prev = null;
        Model next = null;

        public Model() {
        }

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Model getPrev() {
            return prev;
        }

        public void setPrev(Model prev) {
            this.prev = prev;
        }

        public Model getNext() {
            return next;
        }

        public void setNext(Model next) {
            this.next = next;
        }

        @Override
        public Model clone() throws CloneNotSupportedException {
            var clone = (Model) super.clone();
            clone.next = next;
            clone.prev = prev;
            return clone;
        }
    }
}
