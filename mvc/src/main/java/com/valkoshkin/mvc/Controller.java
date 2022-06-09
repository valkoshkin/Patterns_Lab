package com.valkoshkin.mvc;

import com.valkoshkin.mvc.model.Model;
import com.valkoshkin.mvc.model.Record;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

public class Controller {
    @FXML
    private TableView<Record> tableView;

    @FXML
    private TableColumn<Record, Double> xColumn;

    @FXML
    private TableColumn<Record, Double> yColumn;

    @FXML
    private LineChart<Double, Double> lineChart;

    private Model model;

    @FXML
    protected void onAddRowButtonClick() {
        tableView.getItems().add(new Record(0));
    }

    public void onInit() {
        model = new Model(tableView);
        initChart();
        initTable();
    }

    private void initChart() {
        LineChart.Series<Double, Double> series = new LineChart.Series<>();
        lineChart.getData().add(series);
    }

    private void initTable() {
        xColumn.setEditable(true);
        yColumn.setEditable(false);

        model.setInitialRecords(5);

        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));

        var converter = new DoubleStringConverter() {
            @Override
            public Double fromString(String value) {
                try {
                    return super.fromString(value);
                } catch (Exception ignored) {
                }
                return null;
            }
        };

        xColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));
        yColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

        xColumn.setOnEditCommit((TableColumn.CellEditEvent<Record, Double> editEvent) -> {
            TablePosition<Record, Double> position = editEvent.getTablePosition();
            Record record = model.getRecords().get(position.getRow());
            Double value = editEvent.getNewValue();

            if (value != null && !Double.isNaN(value)) {
                record.setX(value);
            } else {
                model.getRecords().remove(record);
            }
            updateChart();
        });
        updateChart();
    }

    private void updateChart() {
        LineChart.Series<Double, Double> series = lineChart.getData().get(0);
        series.getData().clear();
        series.getData().addAll(model.getChartData());
    }
}