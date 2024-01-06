package Service;

import Metier.Article;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.sdbm_02.SDBMController;

import java.util.Objects;

public class EditController {
    private SDBMController sdbmController;
    private static ArticleBean articleBean = new ArticleBean();


    private String originalId, originalNom, originalPrix, originalVolume, originalTitrage, originalStock, originalType, originalCouleur, originalMarque, originalFabricant, originalPays, originalContinent;

    @FXML
    private TextField idTextField, nomTextField, volumnTextField, prixTextField,
            titrageTextField, typeTextField, stockTextField, couleurTextField, marqueTextField,
            fabricantTextField, paysTextField, continentTextField;

    public void setMainController(SDBMController controller) {
        this.sdbmController = controller;
    }


    @FXML
    private void saveChange() {
        // Get the modified data from the input fields
        Integer modifiedID = Integer.valueOf(idTextField.getText());
        String modifiedNom = nomTextField.getText();
        String modifiedPrix = prixTextField.getText();
        String modifiedVolume = volumnTextField.getText();
        String modifiedTitrage = titrageTextField.getText();
        String modifiedStock = stockTextField.getText();
        String modifiedType = typeTextField.getText();
        String modifiedCouleur = couleurTextField.getText();
        String modifiedMarque = marqueTextField.getText();
        String modifiedFabricant = fabricantTextField.getText();
        String modifiedPay = paysTextField.getText();
        String modifiedContinent = continentTextField.getText();

        //create a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save file");
        alert.setHeaderText("Confirm save");
        alert.setContentText("Are you sure you want to save the changes");

        //Show the confirmation dialog and wait for the user's response
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        if (result == ButtonType.OK) {
            updateInfor(modifiedID, modifiedNom, modifiedPrix, modifiedVolume, modifiedTitrage, modifiedStock, modifiedType, modifiedCouleur, modifiedMarque, modifiedFabricant, modifiedPay, modifiedContinent);
            Stage stage = (Stage) nomTextField.getScene().getWindow();
            stage.close();
        }

    }

    public void setOriginalData(String id, String nom, String prix, String volume, String titrage, String stock, String type, String couleur, String marque, String fabricant, String pay, String continent) {
        originalId = id;
        originalNom = nom;
        originalPrix = prix;
        originalVolume = volume;
        originalTitrage = titrage;
        originalStock = stock;
        originalType = type;
        originalCouleur = couleur;
        originalMarque = marque;
        originalFabricant = fabricant;
        originalPays = pay;
        originalContinent = continent;

        // Populate the input fields with the original data
        idTextField.setText(id);
        nomTextField.setText(nom);
        prixTextField.setText(prix);
        volumnTextField.setText(volume);
        titrageTextField.setText(titrage);
        stockTextField.setText(stock);
        typeTextField.setText(type);
        couleurTextField.setText(couleur);
        marqueTextField.setText(marque);
        fabricantTextField.setText(fabricant);
        paysTextField.setText(pay);
        continentTextField.setText(continent);
    }

    public void updateInfor(Integer modifiedID, String modifiedNom, String modifiedPrix, String modifiedVolume, String modifiedTitrage, String modifiedStock,
                            String modifiedType, String modifiedCouleur, String modifiedMarque, String modifiedFabricant, String modifiedPay, String modifiedContinent) {

        for (Article article : articleBean.getAllArticles()) {
            if (Objects.equals(article.getIdArticle(), modifiedID)) {

                article.setNomBiere(modifiedNom);
                article.setPrixBiere(Float.parseFloat(modifiedPrix));
                article.setVolumnBiere(Integer.parseInt(modifiedVolume));
                article.setTitrageBiere(Float.parseFloat(modifiedTitrage));
                article.setStockBiere(Integer.parseInt(modifiedStock));
                article.setNomType(modifiedType);

                if (modifiedCouleur != null && !modifiedCouleur.isEmpty()) {
                    article.setNomCouleur(modifiedCouleur);
                }

                article.setNomMarque(modifiedMarque);
                article.setNomFabricant(modifiedFabricant);
                article.setNomPays(modifiedPay);
                article.setNomContinent(modifiedContinent);


                boolean updateSuccessful = articleBean.getUpdate(article);

                if (!updateSuccessful) {
                  System.err.println("Failed to update article with ID: " + article.getIdArticle());
                } else {
                    System.out.println("Success to update article with ID: " + article.getIdArticle());
                }

                break;
            }
            else {
                article.setIdArticle(modifiedID);
                article.setNomBiere(modifiedNom);
                article.setPrixBiere(Float.parseFloat(modifiedPrix));
                article.setVolumnBiere(Integer.parseInt(modifiedVolume));
                article.setTitrageBiere(Float.parseFloat(modifiedTitrage));
                article.setStockBiere(Integer.parseInt(modifiedStock));
                article.setNomType(modifiedType);

                if (modifiedCouleur != null && !modifiedCouleur.isEmpty()) {
                    article.setNomCouleur(modifiedCouleur);
                }

                article.setNomMarque(modifiedMarque);
                article.setNomFabricant(modifiedFabricant);
                article.setNomPays(modifiedPay);
                article.setNomContinent(modifiedContinent);


                boolean updateSuccessful = articleBean.getInsert(article);

                if (!updateSuccessful) {
                    System.err.println("Failed to insert article with ID: " + article.getIdArticle());
                } else {
                    System.out.println("Success to insert article with ID: " + article.getIdArticle());
                }

                break;
            }
        }
        sdbmController.setupTableView(articleBean.getAllArticles());
    }


}
