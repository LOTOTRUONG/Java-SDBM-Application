package Metier;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;
import java.util.Map;

public class Vendre {
    private idTicket idticket;
    private int IdArticle;
    private int quantite;
    private double prix_vendre;
    private SimpleStringProperty libelle;
    private SimpleIntegerProperty quantity;
    private Map<Integer, SimpleIntegerProperty> yearProperties = new HashMap<>();



    public Vendre(idTicket idticket, int IdArticle, int quantite, double prix_vendre) {
        this.idticket = idticket;
        this.IdArticle = IdArticle;
        this.quantite = quantite;
        this.prix_vendre = prix_vendre;
    }

    public Vendre(idTicket idticket, int quantite) {
        this.idticket = idticket;
        this.quantite = quantite;
    }

    public Vendre(String libelle) {
        this.libelle = new SimpleStringProperty(libelle);
    }
    public String getLibelle() {
        return libelle.get();
    }

    public SimpleStringProperty libelleProperty() {
        return libelle;
    }



    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }
    public SimpleIntegerProperty getYearProperty(int year) {
        return yearProperties.computeIfAbsent(year, k -> new SimpleIntegerProperty());
    }

    public void setQuantity(int year, int quantity) {
        getYearProperty(year).set(quantity);
    }
    public int getQuantityForYear(int year) {
        return yearProperties.getOrDefault(year, new SimpleIntegerProperty(0)).get();
    }

    public idTicket getIdticket() {
        return idticket;
    }
    public void setIdticket(idTicket idticket) {
        this.idticket = idticket;
    }
    public int getIdBiere() {
        return IdArticle;
    }
    public void setIdBiere(int IdArticle) {
        this.IdArticle = IdArticle;
    }
    public int getQuantite() {
        return quantite;
    }
    public double getPrix_vendre() {
        return prix_vendre;
    }
    public void setPrix_vendre(double prix_vendre) {
        this.prix_vendre = prix_vendre;
    }


}

