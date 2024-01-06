package Service;

import DAO.ArticleDAO;
import DAO.DAOFactory;
import Metier.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ArticleBean {
    private final ArticleDAO articleDAO;
    private ArrayList<Couleur> couleursFiltre;
    private ArrayList<Type> typesFiltre;
    private ArrayList<Pays> paysFiltre;
    private ArrayList<Fabricant> fabricantFiltre;
    private ArrayList<Marque> marqueFiltre;
    private ArrayList<Continent> continentFiltre;

    public static ObservableList<Article> articleInputValues = FXCollections.observableArrayList();



    public ArticleBean() {
            couleursFiltre = DAOFactory.getCouleurDAO().getAll();
            typesFiltre = DAOFactory.getTypeDAO().getAll();
            paysFiltre = DAOFactory.getPaysDAO().getAll();
            fabricantFiltre = DAOFactory.getFabricantDAO().getAll();
            marqueFiltre = DAOFactory.getMarqueDAO().getAll();
            continentFiltre = DAOFactory.getContinentDAO().getAll();
            loadAllArticles();
            this.articleDAO = new ArticleDAO();
        }
        public static ObservableList<Article> getAllArticles() {
            return articleInputValues;
        }


    public boolean getInsert(Article object) {
        return articleDAO.insert(object);
    }
    public boolean getUpdate(Article object) {
        return articleDAO.update(object);
    }

    public boolean getDelete(Article object) {
        return articleDAO.delete(object);
    }

        private void loadAllArticles() {
            ArticleDAO articleDAO = DAOFactory.getArticleDAO();
            List<Article> articlesFromDAO = articleDAO.getAll();

            if (articlesFromDAO != null) {
                articleInputValues.addAll(articlesFromDAO);
            }
        }

}




