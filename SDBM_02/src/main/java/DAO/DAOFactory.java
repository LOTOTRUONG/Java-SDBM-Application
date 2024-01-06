package DAO;

public class DAOFactory {
    public static ContinentDAO getContinentDAO(){
        return new ContinentDAO();
    }
    public static CouleurDAO getCouleurDAO() {
        return new CouleurDAO();
    }

    public static TypeDAO getTypeDAO() {
        return new TypeDAO();
    }

    public static PaysDAO getPaysDAO() {
        return new PaysDAO();
    }

    public static ArticleDAO getArticleDAO() {
        return new ArticleDAO();
    }

    public static FabricantDAO getFabricantDAO() {
        return new FabricantDAO();
    }

    public static MarqueDAO getMarqueDAO() {
        return new MarqueDAO();
    }

   // public  static TicketDao getTicketDAO()
    //{
      //  return new TicketDao();
    //}
}
