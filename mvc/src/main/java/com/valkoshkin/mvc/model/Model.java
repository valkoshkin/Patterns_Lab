package com.valkoshkin.mvc.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {
    private final Random random = new Random();
    private final TableView<Record> tableView;

    public Model(TableView<Record> tableView) {
        this.tableView = tableView;
    }

    public void setInitialRecords(int rowsCount) {
        List<Record> itemsList = new ArrayList<>();
        for (int i = 0; i < rowsCount; i++) {
            itemsList.add(new Record(getTruncatedValue(random.nextDouble() * 15 * getRandomSign())));
        }
        tableView.setItems(FXCollections.observableArrayList(itemsList));
    }

    public ObservableList<Record> getRecords() {
        return tableView.getItems();
    }

    public List<LineChart.Data<Double, Double>> getChartData() {
        var list = new ArrayList<LineChart.Data<Double, Double>>();
        for (Record record : tableView.getItems()) {
            list.add(new LineChart.Data<>(record.getX(), record.getY()));
        }
        return list;
    }

    private double getTruncatedValue(double value) {
        return Math.floor(value * 100) / 100;
    }

    private int getRandomSign() {
        return random.nextDouble() <= 0.5 ? 1 : -1;
    }
}
