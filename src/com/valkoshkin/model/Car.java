package com.valkoshkin.model;

import com.valkoshkin.exceptions.DuplicateModelNameException;
import com.valkoshkin.exceptions.ModelPriceOutOfBoundsException;
import com.valkoshkin.exceptions.NoSuchModelNameException;

import java.io.Serializable;
import java.util.Arrays;

public class Car implements Transport {
    private String brand;
    private Model[] models;

    public Car(String brand, int modelsLength) {
        this.brand = brand;
        this.models = new Model[modelsLength];
    }

    private boolean isModelWithNameExist(String name) {
        int index = 0;
        while (index < models.length) {
            if (models[index] != null && models[index].name.equals(name)) {
                return true;
            }
            index++;
        }
        return false;
    }

    private int getFirstNullModelIndex() {
        for (int i = 0; i < models.length; i++) {
            if (models[i] == null) {
                return i;
            }
        }
        return models.length;
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
        int existedModelsLength = 0;
        for (Model model: models) {
            if (model != null) existedModelsLength++;
        }
        return existedModelsLength;
    }

    @Override
    public String[] getModelsNames() {
        String[] modelsNames = new String[models.length];
        for (int i = 0; i < models.length; i++) {
            if (models[i] != null) {
                modelsNames[i] = models[i].name;
            }
        }
        return modelsNames;
    }

    @Override
    public void setModelNameByName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        if (isModelWithNameExist(newName)) {
            throw new DuplicateModelNameException(String.format("Model with name '%s' already exist.", newName));
        }
        int index = 0;
        while (index < models.length) {
            if (models[index] != null && models[index].name.equals(oldName)) {
                models[index].name = newName;
                return;
            }
            index++;
        }
        throw new NoSuchModelNameException(String.format("Model with name '%s' not found.", oldName));
    }

    @Override
    public double getModelPriceByName(String name) throws NoSuchModelNameException {
        int index = 0;
        while (index < models.length) {
            if (models[index] != null && models[index].name.equals(name)) {
                return models[index].price;
            }
            index++;
        }
        throw new NoSuchModelNameException(String.format("Model with name '%s' not found.", name));
    }

    @Override
    public void setModelPriceByName(String name, double price) throws NoSuchModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("The price must be a positive number.");
        }
        int index = 0;
        while (index < models.length) {
            if (models[index] != null && models[index].name.equals(name)) {
                models[index].price = price;
                return;
            }
            index++;
        }
        throw new NoSuchModelNameException(String.format("Model with name '%s' not found.", name));
    }

    @Override
    public double[] getModelsPrices() {
        double[] modelsPrices = new double[models.length];
        for (int i = 0; i < models.length; i++) {
            if (models[i] != null) {
                modelsPrices[i] = models[i].price;
            }
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
        Model model = new Model(name, price);
        int firstNullModelIndex = getFirstNullModelIndex();
        if (firstNullModelIndex == models.length) {
            models = Arrays.copyOf(models, models.length + 1);
        }
        models[firstNullModelIndex] = model;
    }

    @Override
    public void deleteModel(String name) throws NoSuchModelNameException {
        boolean isModelFounded = false;
        int index = -1;
        while (!isModelFounded && index < models.length - 1) {
            index++;
            if (models[index] != null && models[index].name.equals(name)) {
                isModelFounded = true;
            }
        }
        if (isModelFounded) {
            if (index != models.length - 1) {
                System.arraycopy(models, ++index, models, --index, models.length - 1 - index);
            }
            models = Arrays.copyOf(models, models.length - 1);
        } else {
            throw new NoSuchModelNameException(String.format("Model with name '%s' not found.", name));
        }
    }

    @Override
    public Car clone() throws CloneNotSupportedException {
        var clone = (Car) super.clone();
        var clonedModels = new Model[models.length];
        for (int i = 0; i < models.length; i++) {
            clonedModels[i] = models[i].clone();
        }
        clone.models = clonedModels;
        return clone;
    }

    private class Model implements Cloneable {
        private String name;
        private double price;

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

        @Override
        public Model clone() throws CloneNotSupportedException {
            return (Model) super.clone();
        }
    }
}
