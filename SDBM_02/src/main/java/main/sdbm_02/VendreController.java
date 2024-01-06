package main.sdbm_02;

import Metier.Vendre;
import Service.VendreStatistic;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.*;

public class VendreController {
    @FXML
    private ComboBox<String> chooseBox;
    @FXML
    private LineChart<String, Integer> lineChart;
    @FXML
    private TableColumn<Vendre, String> columnName;
    @FXML
    private TableColumn<Vendre, Integer> column1,column2,column3,column4,column5,column6,column7,column8,column9;

    @FXML
    private TableView<Vendre> dataTable;
    private String selectedCatalogie = null;


    public void initialize() {
        populateComboBox();
        LineChartVendreParAnneeParCouleur();
        setupDataTableSelectionListener();
    }
    private void populateComboBox() {
        ObservableList<String> statisticTypes = FXCollections.observableArrayList(
                "Vendre Par Annee Par Couleur",
                "Vendre Par Annee Par Type",
                "Vendre Par Annee Par Continent"
        );
        chooseBox.setItems(statisticTypes);
        chooseBox.getSelectionModel().selectFirst(); // Select the first item by default
        chooseBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Handle the selected statistic type change
            if (newValue != null) {
                handleSelectedStatisticType(newValue);
            }
        });
    }

    private void handleSelectedStatisticType(String selectedStatisticType) {
        if ("Vendre Par Annee Par Couleur".equals(selectedStatisticType)) {
            VendreStatistic vendreStatistic = new VendreStatistic();
            Map<String, Map<Integer, Integer>> quantityByAnneeAndCouleur = vendreStatistic.getQuantiteByAnneeAndCouleur();
            populateLineChart(quantityByAnneeAndCouleur);
            updateDataTable(quantityByAnneeAndCouleur);
        } else if ("Vendre Par Annee Par Type".equals(selectedStatisticType)) {
            VendreStatistic vendreStatistic = new VendreStatistic();
            Map<String, Map<Integer,Integer>> quantityByAnneeAndType = vendreStatistic.getQuantiteByAnneeAndType();
            populateLineChart(quantityByAnneeAndType);
            updateDataTable(quantityByAnneeAndType);
        } else if ("Vendre Par Annee Par Continent".equals(selectedStatisticType)) {
            VendreStatistic vendreStatistic = new VendreStatistic();
            Map<String, Map<Integer,Integer>> quantityByAnneeAndType = vendreStatistic.getQuantiteByAnneeAndContinent();
            populateLineChart(quantityByAnneeAndType);
            updateDataTable(quantityByAnneeAndType);
        }
    }
    private void LineChartVendreParAnneeParCouleur() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setTickLabelFont(Font.font("Arial", FontWeight.BOLD, 18));
        NumberAxis yAxis = new NumberAxis();
        yAxis.setTickLabelFont(Font.font("Arial", FontWeight.BOLD, 18));
        lineChart.setTitle("Quantity by Year");
        lineChart.getXAxis().setLabel("Year");
        lineChart.getYAxis().setLabel("Quantity");


        VendreStatistic vendreStatistic = new VendreStatistic();
        Map<String, Map<Integer, Integer>> quantityByAnneeAndCouleur = vendreStatistic.getQuantiteByAnneeAndCouleur();
        populateLineChart(quantityByAnneeAndCouleur);
        updateDataTable(quantityByAnneeAndCouleur);
    }

    private void populateLineChart(Map<String, Map<Integer, Integer>> data) {
        lineChart.getData().clear(); // Clear existing data


        for (Map.Entry<String, Map<Integer, Integer>> entry : data.entrySet()) {
            String libelle = entry.getKey();
            if (libelle == null) {
                continue;
            }
            Map<Integer, Integer> yearData = entry.getValue();
            XYChart.Series<String, Integer> series = getOrCreateSeries(libelle);
            List<Integer> years = new ArrayList<>(yearData.keySet());
            Collections.sort(years);
            for (Integer year : years) {
                Integer quantity = yearData.get(year);
                series.getData().add(new XYChart.Data<>(String.valueOf(year), quantity));
            }
        }
    }

    private XYChart.Series<String, Integer> getOrCreateSeries(String seriesName) {
        for (XYChart.Series<String, Integer> series : lineChart.getData()) {
            if ((series.getName() != null && series.getName().equals(seriesName)) || (series.getName() == null && seriesName == null)) {
                return series;
            }
        }
        XYChart.Series<String, Integer> newSeries = new XYChart.Series<>();
        newSeries.setName(seriesName != null ? seriesName : "NULL");
        lineChart.getData().add(newSeries);
        return newSeries;
    }
    private void updateDataTable(Map<String, Map<Integer, Integer>> data) {
        ObservableList<Vendre> vendreObservableList = FXCollections.observableArrayList();

        for (Map.Entry<String, Map<Integer, Integer>> entry : data.entrySet()) {
            String libelle = entry.getKey();
            Map<Integer, Integer> yearData = entry.getValue();
            if (libelle == null) {
                libelle = "NULL";
            }
            Vendre vendre = new Vendre(libelle);

            for (int year : yearData.keySet()) {
                int quantity = yearData.get(year);
                vendre.setQuantity(year, quantity);
            }

            vendreObservableList.add(vendre);
        }

        // Set cell values for each column
        columnName.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        columnName.setText("Color Name");
        column1.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantityForYear(2014)).asObject());
        column1.setText("Quantity in 2014");
        column2.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantityForYear(2015)).asObject());
        column2.setText("Quantity in 2015");
        column3.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantityForYear(2016)).asObject());
        column3.setText("Quantity in 2016");
        column4.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantityForYear(2017)).asObject());
        column4.setText("Quantity in 2017");
        column5.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantityForYear(2018)).asObject());
        column5.setText("Quantity in 2018");
        column6.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantityForYear(2019)).asObject());
        column6.setText("Quantity in 2019");
        column7.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantityForYear(2020)).asObject());
        column7.setText("Quantity in 2020");
        column8.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantityForYear(2021)).asObject());
        column8.setText("Quantity in 2021");
        column9.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantityForYear(2022)).asObject());
        column9.setText("Quantity in 2022");

        // Set the items in the dataTable
        dataTable.setItems(vendreObservableList);
    }

    private void setupDataTableSelectionListener() {
        dataTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        dataTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Vendre>() {
            @Override
            public void changed(ObservableValue<? extends Vendre> observable, Vendre oldValue, Vendre selectedVendre) {
                if (selectedVendre != null) {
                    selectedCatalogie = selectedVendre.getLibelle();
                    updateLineChartForSelectedVendre(selectedVendre);
                }
            }
        });
    }
    private void updateLineChartForSelectedVendre(Vendre selectedVendre) {
        lineChart.getData().forEach(series -> {
            String seriesCatalogie = series.getName();
            series.getData().forEach(data -> {
                Node node = data.getNode();
                if (seriesCatalogie.equals(selectedVendre.getLibelle())) {
                    node.setStyle("-fx-background-color: " + toValidColor(selectedCatalogie));
                } else {
                    node.setStyle("");
                }
            });
        });

    }
    private String toValidColor(String color) {
        try {
            Color.web(color);  // Validate the color
            return color;
        } catch (IllegalArgumentException e) {
            // Use a default color or handle the error as needed
            return "red";  // Default to black if the color is invalid
        }
    }
}
