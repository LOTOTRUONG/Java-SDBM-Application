package main.sdbm_02;


import DAO.*;
import Metier.*;
import Service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.RangeSlider;
import org.controlsfx.control.SearchableComboBox;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SDBMController {
    @FXML
    private RangeSlider searchTitrage;
    @FXML
    private TextField paysArticle, continentArticle, couleurArticle, typeArticle, idArticle, nomArticle, prixArticle, volumeArticle, titrageArticle, stockArticle, marqueArticle, fabricantArticle;
    @FXML
    private SearchableComboBox<String> searchContinent, searchCouleur, searchFabricant, searchMarque, searchNom, searchPays, searchType, searchVolumn;
    @FXML
    private TableView<Article> tableArticle;
    @FXML
    private TableColumn<Article, Integer> idArticleColumn;
    @FXML
    private TableColumn<Article, String> nomBiereColumn;
    @FXML
    private TableColumn<Article, Integer> volumnBiereColumn;
    @FXML
    private TableColumn<Article, Float> titrageBiereColumn;
    private Article selectedArticle;
    private ArticleBean articleBean;

    @FXML
    void insertData() {
        try{
            FXMLLoader editLoader = new FXMLLoader(getClass().getResource("functionSBDM.fxml"));
            Parent editRoot = editLoader.load();
            EditController edit = editLoader.getController();
            edit.setMainController(this);
            Stage editstage = new Stage();
            editstage.setTitle("Adding scence");
            editstage.setScene(new Scene(editRoot));
            editstage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading modify scene");
        }
    }

    @FXML
    void deleteData() {
        if (selectedArticle != null) {
            Alert deleteConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmation.setTitle("Delete");
            deleteConfirmation.setHeaderText("Confirmation the delete");
            deleteConfirmation.setContentText("Are you sure you want to delete this information?");

            Optional<ButtonType> result = deleteConfirmation.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Clear the text fields
                nomArticle.clear();
                idArticle.clear();
                titrageArticle.clear();
                volumeArticle.clear();
                couleurArticle.clear();
                stockArticle.clear();
                typeArticle.clear();
                paysArticle.clear();
                continentArticle.clear();
                fabricantArticle.clear();
                marqueArticle.clear();
                prixArticle.clear();
                // Remove the selected contact from the table view
                tableArticle.getItems().remove(selectedArticle);
                // method to save to the sql sever
                articleBean.getDelete(selectedArticle);

            }
        }
    }

    @FXML
    public void modifyData() {
        try{
            FXMLLoader editLoader = new FXMLLoader(getClass().getResource("functionSBDM.fxml"));
            Parent editRoot = editLoader.load();
            EditController edit = editLoader.getController();
            edit.setMainController(this);
            edit.setOriginalData(idArticle.getText(), nomArticle.getText(),prixArticle.getText(),volumeArticle.getText(),titrageArticle.getText(), stockArticle.getText(),typeArticle.getText(),couleurArticle.getText(),marqueArticle.getText(),fabricantArticle.getText(),paysArticle.getText(),continentArticle.getText());
            Stage editstage = new Stage();
            editstage.setTitle("Modify scence");
            editstage.setScene(new Scene(editRoot));
            editstage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading modify scene");
        }
    }

    public void initialize() {
        articleBean = new ArticleBean();
        searchComboBOX();
        //all article in tableview
        setupTableView(articleBean.getAllArticles());
    }

    public void searchComboBOX() {
        ObservableList<String> nomBiereList = extractNomBiereValues(articleBean.getAllArticles());
        nomBiereList.add(0, null);
        setupSearchController(searchNom, nomBiereList);

        ObservableList<String> volumList = extractVolumeValues(articleBean.getAllArticles());
        volumList.add(0, null);
        setupSearchController(searchVolumn, volumList);

        //Call the observableList for the comboBox
        List<Couleur> couleurList = (new CouleurDAO()).getAll();
        ObservableList<String> couleurObservableList = FXCollections.observableArrayList();
        couleurObservableList.add(0, null);

        List<Type> typeList = (new TypeDAO()).getAll();
        ObservableList<String> typeObservableList = FXCollections.observableArrayList();
        typeObservableList.add(0, null);

        List<Fabricant> fabricantList = (new FabricantDAO()).getAll();
        ObservableList<String> fabricantObservableList = FXCollections.observableArrayList();
        fabricantObservableList.add(0, null);

        List<Marque> marqueList = (new MarqueDAO()).getAll();
        ObservableList<String> marqueObservableList = FXCollections.observableArrayList();
        marqueObservableList.add(0, null);

        List<Pays> payList = (new PaysDAO()).getAll();
        ObservableList<String> payObservableList = FXCollections.observableArrayList();
        payObservableList.add(0, null);

        List<Continent> continentList = (new ContinentDAO()).getAll();
        ObservableList<String> continentObservableList = FXCollections.observableArrayList();
        continentObservableList.add(0, null);

        //search Couleur
        for (Couleur couleur : couleurList) {
            couleurObservableList.add(couleur.getNomCouleur());
        }
        setupSearchController(searchCouleur, couleurObservableList);

        //Search type
        for (Type type : typeList) {
            typeObservableList.add(type.getLibelle());
        }
        setupSearchController(searchType, typeObservableList);

        //search fabricant
        for (Fabricant fabricant : fabricantList) {
            fabricantObservableList.add(fabricant.getNomFabricant());
        }
        searchFabricant.setItems(fabricantObservableList);
        setupSearchController(searchFabricant, fabricantObservableList);

        //search Marque
        for (Marque marque : marqueList) {
            marqueObservableList.add(marque.getLibelle());
        }
        searchMarque.setItems(marqueObservableList);

        //search Pays
        payList.forEach(pays -> payObservableList.add(pays.getLibelle()));
        searchPays.setItems(payObservableList);
        setupSearchController(searchPays, payObservableList);
        searchPays.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                Pays selectedPays = (new PaysDAO()).getByLibelle(newVal);
                List<Marque> marqueSelected = (selectedPays != null) ? new MarqueDAO().getByPays(selectedPays):List.of();
                ObservableList<String> marqueSelectedList = FXCollections.observableArrayList();
                for (Marque marque : marqueSelected) {
                    marqueSelectedList.add(marque.getLibelle());
                }
                searchMarque.setItems(marqueSelectedList);
                searchMarque.getSelectionModel().selectFirst();
                updateTableView();
            } else {
                searchMarque.setItems(marqueObservableList);
            }
        });

        //search Continent
        continentList.forEach(continent -> continentObservableList.add(continent.getNomContinent()));
        searchContinent.setItems(continentObservableList);
        setupSearchController(searchContinent, continentObservableList);
        searchContinent.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                Continent selectedContinent = (new ContinentDAO()).getByLibelle(newVal);
                List<Pays> paySelected = (selectedContinent != null) ? new PaysDAO().getByContinent(selectedContinent):List.of();
                    ObservableList<String> paySelectedList = FXCollections.observableArrayList();
                    for (Pays pay : paySelected) {
                        paySelectedList.add(pay.getLibelle());
                    }
                    searchPays.setItems(paySelectedList);
                    searchPays.getSelectionModel().selectFirst();
                    updateTableView();
            } else {
                searchPays.setItems(payObservableList);
            }
        });


        //search Titrage
        searchTitrage.lowValueProperty().addListener((observableValue, oldValue, newValue) -> {
            updateTableView();
        });
        searchTitrage.highValueProperty().addListener((observable, oldValue, newValue) -> {
            updateTableView();
        });
    }



    public void setupTableView(ObservableList<Article> articles) {
        idArticleColumn.setCellValueFactory(cellData -> cellData.getValue().idArticleProperty().asObject());
        nomBiereColumn.setCellValueFactory(cellData -> cellData.getValue().nomBiereProperty());
        volumnBiereColumn.setCellValueFactory(cellData -> cellData.getValue().volumnBiereProperty().asObject());
        titrageBiereColumn.setCellValueFactory(cellData -> cellData.getValue().titrageBiereProperty().asObject());
        tableArticle.setItems(articles);
    }



    @FXML
    void getItem(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 1) {
            selectedArticle = tableArticle.getSelectionModel().getSelectedItem();
            if (selectedArticle != null) {
                //Set value from the selected contact to the textfields
                idArticle.setText(String.valueOf(selectedArticle.getIdArticle()));
                nomArticle.setText(selectedArticle.getNomBiere());
                prixArticle.setText(String.valueOf(selectedArticle.getprixBiere()));
                titrageArticle.setText(selectedArticle.getTitrageBiere() != 0 ? String.valueOf(selectedArticle.getTitrageBiere()) : "NULL");
                volumeArticle.setText(String.valueOf(selectedArticle.getVolumnBiere()));
                stockArticle.setText(String.valueOf(selectedArticle.getStockBiere()));
                couleurArticle.setText(selectedArticle.getnomCouleur() != null ? selectedArticle.getnomCouleur() : "NULL");
                typeArticle.setText(selectedArticle.getnomType() != null ? selectedArticle.getnomType() : "NULL");
                fabricantArticle.setText(selectedArticle.getnomFabricant() != null ? selectedArticle.getnomFabricant() : "NULL");
                marqueArticle.setText(selectedArticle.getnomMarque() != null ? selectedArticle.getnomMarque() : "NULL");
                paysArticle.setText(selectedArticle.getnomPays() != null ? selectedArticle.getnomPays() : "NULL");
                continentArticle.setText(selectedArticle.getnomContinent() != null ? selectedArticle.getnomContinent(): "NULL");
            }
        }
    }

    private ObservableList<String> extractNomBiereValues(ObservableList<Article> articles) {
        return ArticleValuesExtractor.extractNomBiereValues(articles);
    }
    private ObservableList<String> extractVolumeValues(ObservableList<Article> articles) {
        return ArticleValuesExtractor.extractVolumeValues(articles);
    }


    private void setupSearchController(SearchableComboBox<String> comboBox, ObservableList<String> values) {
        Search searchController = new Search(comboBox, values);
        ObservableList<String> modifiedValues = FXCollections.observableArrayList();
        modifiedValues.addAll(values);
        comboBox.setItems(modifiedValues);
        comboBox.getSelectionModel().selectFirst();
        comboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            updateTableView();
        });

    }


    private void updateTableView() {
        double minTitrage = searchTitrage.getLowValue();
        double maxTitrage = searchTitrage.getHighValue();
        ObservableList<Article> filteredArticles = FXCollections.observableArrayList();
        for (Article article : articleBean.getAllArticles()) {
            boolean match = true;
            double titrage = article.getTitrageBiere();

            if (searchNom.getValue() != null && !searchNom.getValue().isEmpty()) {
                match = match && Objects.equals(article.getNomBiere(), searchNom.getValue());}
            if (searchVolumn.getValue() != null && !searchVolumn.getValue().isEmpty()) {
                match = match && Objects.equals(String.valueOf(article.getVolumnBiere()), searchVolumn.getValue());}
            if (searchCouleur.getValue() != null && !searchCouleur.getValue().isEmpty()) {
                match = match && Objects.equals(article.getnomCouleur(), searchCouleur.getValue());}
            if (searchType.getValue() != null && !searchType.getValue().isEmpty()) {
                match = match && Objects.equals(article.getnomType(), searchType.getValue());}
            if (searchMarque.getValue() != null && !searchMarque.getValue().isEmpty()) {
                match = match && Objects.equals(article.getnomMarque(), searchMarque.getValue());}
            if (searchFabricant.getValue() != null && !searchFabricant.getValue().isEmpty()) {
                match = match && Objects.equals(article.getnomFabricant(), searchFabricant.getValue());}
            if (searchPays.getValue() != null && !searchPays.getValue().isEmpty()) {
                match = match && Objects.equals(article.getnomPays(), searchPays.getValue());}
            if (searchContinent.getValue() != null && !searchContinent.getValue().isEmpty()) {
                match = match && Objects.equals(article.getnomContinent(), searchContinent.getValue());}
            if(titrage >= minTitrage && titrage <= maxTitrage) {
                match = match && Objects.equals(article.getTitrageBiere(), titrage);
            }

            if (match) {
                filteredArticles.add(article);}
        }

        // Update TableView with filtered articles or all articles if no value is selected
        if (!filteredArticles.isEmpty()) {
            tableArticle.setItems(filteredArticles);
        } else {
            tableArticle.setItems(articleBean.getAllArticles());
        }
    }


}