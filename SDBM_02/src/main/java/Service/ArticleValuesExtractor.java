package Service;

import Metier.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArticleValuesExtractor {
    public static ObservableList<String> extractNomBiereValues(ObservableList<Article> articles) {
        ObservableList<String> nomBiereValues = FXCollections.observableArrayList();
        for (Article article : articles) {
            nomBiereValues.add(article.getNomBiere());
        }
        return nomBiereValues;
    }
    public static ObservableList<String> extractVolumeValues(ObservableList<Article> articles) {
        Set<String> volumeBiereValues = new HashSet<>();
        for (Article article : articles) {
            volumeBiereValues.add(String.valueOf(article.getVolumnBiere()));
        }
        return FXCollections.observableArrayList(volumeBiereValues);
    }




}
