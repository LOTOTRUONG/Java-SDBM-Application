package Metier;

import javafx.beans.property.*;

public class Article {
    private final IntegerProperty idArticle;
    private final StringProperty nomBiere;
    private final IntegerProperty volumnBiere;
    private final FloatProperty titrageBiere;
    private final FloatProperty prixBiere;
    private final IntegerProperty stockBiere;
    private final IntegerProperty idCouleur;
    private final StringProperty nomCouleur;
    private final IntegerProperty idType;
    private final StringProperty nomType;
    private final IntegerProperty idFabricant;
    private final StringProperty nomFabricant;
    private final IntegerProperty idMarque;
    private final StringProperty nomMarque;
    private final IntegerProperty idPays;
    private final StringProperty nomPays;
    private final IntegerProperty idContinent;
    private final StringProperty nomContinent;
    public Article(int idArticle, String nomBiere, float prixBiere, int volumnBiere, float titrageBiere, int stockBiere,int idType, String nomType, int idCouleur, String nomCouleur,int idMarque, String nomMarque,int idFabricant, String nomFabricant,int idPays, String nomPays, int idContinent, String nomContinent) {
        this.idArticle = new SimpleIntegerProperty(idArticle);
        this.nomBiere = new SimpleStringProperty(nomBiere);
        this.prixBiere = new SimpleFloatProperty(prixBiere);
        this.titrageBiere = new SimpleFloatProperty(titrageBiere);
        this.volumnBiere = new SimpleIntegerProperty(volumnBiere);
        this.stockBiere = new SimpleIntegerProperty(stockBiere);
        this.idCouleur = new SimpleIntegerProperty(idCouleur);
        this.nomCouleur = new SimpleStringProperty(nomCouleur);
        this.idType = new SimpleIntegerProperty(idType);
        this.nomType = new SimpleStringProperty(nomType);
        this.idFabricant = new SimpleIntegerProperty(idFabricant);
        this.nomFabricant = new SimpleStringProperty(nomFabricant);
        this.idMarque = new SimpleIntegerProperty(idMarque);
        this.nomMarque = new SimpleStringProperty(nomMarque);
        this.idPays = new SimpleIntegerProperty(idPays);
        this.nomPays = new SimpleStringProperty(nomPays);
        this.idContinent = new SimpleIntegerProperty(idContinent);
        this.nomContinent = new SimpleStringProperty(nomContinent);


    }
    public void setIdArticle(int idArticle) {
        this.idArticle.set(idArticle);
    }
    public int getIdArticle() {
        return idArticle.get();
    }
    public IntegerProperty idArticleProperty() {
        return idArticle;
    }
    public void setNomBiere(String nomBiere) {
        this.nomBiere.set(nomBiere);
    }
    public String getNomBiere() {
        return nomBiere.get();
    }
    public StringProperty nomBiereProperty() {
        return nomBiere;
    }
    public void setVolumnBiere(int volumnBiere) {
        this.volumnBiere.set(volumnBiere);
    }
    public int getVolumnBiere() {
        return volumnBiere.get();
    }
    public IntegerProperty volumnBiereProperty() {
        return volumnBiere;
    }

    public void setTitrageBiere(float titrageBiere){
        this.titrageBiere.set(titrageBiere);
    }
    public FloatProperty titrageBiereProperty() {
        return titrageBiere;
    }
    public float getTitrageBiere() {
        return titrageBiere.get();
    }

    public void setPrixBiere(float prixBiere){
        this.prixBiere.set(prixBiere);
    }
    public FloatProperty prixBiereProperty() {
        return prixBiere;
    }
    public float getprixBiere() {
        return prixBiere.get();
    }

    public void setStockBiere(int stockBiere) {
        this.stockBiere.set(stockBiere);
    }
    public int getStockBiere() {
        return stockBiere.get();
    }
    public IntegerProperty stockBiereProperty() {
        return stockBiere;
    }
    public void setIdCouleur(int idCouleur) {
        this.idCouleur.set(idCouleur);
    }
    public int getIdCouleur() {
        return idCouleur.get();
    }
    public IntegerProperty idCouleurProperty(){
        return idCouleur;
    }
    public void setNomCouleur(String nomCouleur) {
        this.nomCouleur.set(nomCouleur);
    }
    public String getnomCouleur(){
        return nomCouleur.get();
    }
    public StringProperty nomCouleurProperty(){
        return nomCouleur;
    }
    public void setIdType(int idType) {
        this.idType.set(idType);
    }

    public int getIdType() {
        return idType.get();
    }

    public IntegerProperty idTypeProperty() {
        return idType;
    }

    public void setNomType(String nomCouleur) {
        this.nomType.set(nomCouleur);
    }

    public String getnomType() {
        return nomType.get();
    }

    public StringProperty nomTypeProperty() {
        return nomType;
    }
    public void setIdFabricant(int idFabricant) {
        this.idFabricant.set(idFabricant);
    }

    public int getIdFabricant() {
        return idFabricant.get();
    }

    public IntegerProperty idFabricantProperty() {
        return idFabricant;
    }

    public void setNomFabricant(String nomFabricant) {
        this.nomFabricant.set(nomFabricant);
    }

    public String getnomFabricant() {
        return nomFabricant.get();
    }

    public StringProperty nomFabricantProperty() {
        return nomFabricant;
    }
    public void setIdMarque(int idMarque) {
        this.idMarque.set(idMarque);
    }

    public int getIdMarque() {
        return idMarque.get();
    }

    public IntegerProperty idMarqueProperty() {
        return idMarque;
    }

    public void setNomMarque(String nomMarque) {
        this.nomMarque.set(nomMarque);
    }

    public String getnomMarque() {
        return nomMarque.get();
    }

    public StringProperty nomMarqueProperty() {
        return nomMarque;
    }
    public void setIdPays(int idPays) {
        this.idPays.set(idPays);
    }

    public int getIdPays() {
        return idPays.get();
    }

    public IntegerProperty idPaysProperty() {
        return idPays;
    }

    public void setNomPays(String nomCouleur) {
        this.nomPays.set(nomCouleur);
    }

    public String getnomPays() {
        return nomPays.get();
    }

    public StringProperty nomPaysProperty() {
        return nomPays;
    }
    public void setIdContinent(int idContinent) {
        this.idContinent.set(idContinent);
    }

    public int getIdContinent() {
        return idContinent.get();
    }

    public IntegerProperty idContinentProperty() {
        return idContinent;
    }

    public void setNomContinent(String nomContinent) {
        this.nomContinent.set(nomContinent);
    }

    public String getnomContinent() {
        return nomContinent.get();
    }

    public StringProperty nomContinentProperty() {
        return nomContinent;
    }
}
