package Service;


import DAO.ArticleDAO;
import DAO.VendreDAO;
import Metier.Article;
import Metier.Vendre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendreStatisticTest {
            VendreDAO vendreDAO = new VendreDAO();

           private final List<Vendre> vendreList = vendreDAO.getAllVendre();

        public VendreStatisticTest() {

        }
        public Map<String, Integer> getQuantiteByAnnee() {
            Map<String, Integer> statistics = new HashMap<>();
            for (Vendre vendre : vendreList) {
                int annee = vendre.getIdticket().getAnnee();
                int quantity = vendre.getQuantite();

                statistics.merge(String.valueOf(annee), quantity, Integer::sum);
            }
            return statistics;
        }

    public Map<String, Map<String, Float>> getQuantiteByAnneeAndCouleur() {
        Map<String, Map<String, Float>> statistics = new HashMap<>();

        for (Vendre vendre : vendreList) {
            String annee = String.valueOf(vendre.getIdticket().getAnnee());
            int idArticle = vendre.getIdBiere();
            Article article = getCouleurByIdArticle(idArticle);
            String couleur = article != null ? article.getnomCouleur() : "Unknown";
            float totalQuantite = vendre.getQuantite();

            statistics.computeIfAbsent(annee, k -> new HashMap<>());
            statistics.get(annee).merge(couleur, totalQuantite, Float::sum);
        }

        return statistics;
        }

    private Article getCouleurByIdArticle(Integer id) {
        ArticleDAO articleDAO = new ArticleDAO();
        return articleDAO.getByID(id);
    }

}


