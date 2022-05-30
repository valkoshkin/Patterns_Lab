package com.valkoshkin.model;

import com.valkoshkin.exceptions.DuplicateModelNameException;
import com.valkoshkin.exceptions.NoSuchModelNameException;

public class SynchronizedTransport implements Transport {
    private final Transport transport;

    public SynchronizedTransport (Transport transport) {
        this.transport = transport;
    }

    @Override
    public String getBrand() {
        return transport.getBrand();
    }

    @Override
    public synchronized void setBrand(String brand) {
        transport.setBrand(brand);
    }

    @Override
    public int getModelsLength() {
        return transport.getModelsLength();
    }

    @Override
    public String[] getModelsNames() {
        return transport.getModelsNames();
    }

    @Override
    public synchronized void setModelNameByName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        transport.setModelNameByName(oldName, newName);
    }

    @Override
    public double getModelPriceByName(String name) throws NoSuchModelNameException {
        return transport.getModelPriceByName(name);
    }

    @Override
    public synchronized void setModelPriceByName(String name, double price) throws NoSuchModelNameException {
        transport.setModelPriceByName(name, price);
    }

    @Override
    public double[] getModelsPrices() {
        return transport.getModelsPrices();
    }

    @Override
    public synchronized void addModel(String name, double price) throws DuplicateModelNameException {
        transport.addModel(name, price);
    }

    @Override
    public synchronized void deleteModel(String name) throws NoSuchModelNameException {
        transport.deleteModel(name);
    }

    @Override
    public Transport clone() throws CloneNotSupportedException {
        return new SynchronizedTransport(transport.clone());
    }
}
