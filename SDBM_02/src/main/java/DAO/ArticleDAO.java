package DAO;

import Metier.Article;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticleDAO extends DAO<Article, Article, Integer> {
    @Override
    public Article getByID(Integer id) {
        String sqlRequest = "{call ps_AllTABLE}";
        Article article = null;
        try (CallableStatement callableStatement = connection.prepareCall(sqlRequest)) {
            //callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next())
                return new Article(resultSet.getInt(1), resultSet.getString(2), resultSet.getFloat(3), resultSet.getInt(4), resultSet.getFloat(5), resultSet.getInt(6),resultSet.getInt(7),
                        resultSet.getString(8), resultSet.getInt(9),resultSet.getString(10), resultSet.getInt(11),resultSet.getString(12),resultSet.getInt(13),resultSet.getString(14),
                        resultSet.getInt(15),resultSet.getString(16),resultSet.getInt(17),resultSet.getString(18));
            return null;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Article> getAll() {
        ArrayList<Article> liste = new ArrayList<>();
        String sqlRequest = "{call ps_AllTABLE}";
        try {
            CallableStatement callableStatement = connection.prepareCall(sqlRequest);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID_biere");
                String nomBiere = resultSet.getString("Nom_biere");
                float prixachat = resultSet.getFloat("prix_d_achat");
                int volumn = resultSet.getInt("volume");
                float titrage = resultSet.getFloat("titrage");
                int stock = resultSet.getInt("stock");
                int idType = resultSet.getInt("IDTYPE");
                String nomType = resultSet.getString("TYPE");
                int idCouleur = resultSet.getInt("IDCOULEUR");
                String nomCouleur = resultSet.getString("COULEUR");
                int idFabricant = resultSet.getInt("IDFABRICANT");
                int idMarque = resultSet.getInt("IDMARQUE");
                String nomMarque = resultSet.getString("MARQUE");
                String nomFabricant = resultSet.getString("fabricant");
                int idPays = resultSet.getInt("IDPAYS");
                String nomPays = resultSet.getString("PAY");
                int idContinent = resultSet.getInt("IDCONTINENT");
                String nomContinent = resultSet.getString("CONTINENT");


                Article article = new Article(id, nomBiere, prixachat, volumn, titrage, stock,idType,nomType, idCouleur,nomCouleur,idMarque,nomMarque,idFabricant,nomFabricant,idPays,nomPays,idContinent,nomContinent);
                liste.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Article> getLike(Article object) {
        String sqlCommand = "{call ps_AllTABLE (?)}";
        ArrayList<Article> liste = new ArrayList<>();
        try (CallableStatement callableStatement = connection.prepareCall(sqlCommand)) {
            callableStatement.setString(2, "%" + object.getNomBiere() + "%");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                liste.add(new Article(resultSet.getInt(1), resultSet.getString(2), resultSet.getFloat(3), resultSet.getInt(4), resultSet.getFloat(5), resultSet.getInt(6),resultSet.getInt(7),
                        resultSet.getString(8), resultSet.getInt(9),resultSet.getString(10), resultSet.getInt(11),resultSet.getString(12),resultSet.getInt(13),resultSet.getString(14),
                        resultSet.getInt(15),resultSet.getString(16),resultSet.getInt(17),resultSet.getString(18)));
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public boolean insert(Article object) {
        String updateQuery = "{call ps_InsertArticle(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)}";
        try (CallableStatement callableStatement = connection.prepareCall(updateQuery))
        {
            callableStatement.setInt(1, object.getIdArticle());
            callableStatement.setString(2, object.getNomBiere());
            callableStatement.setFloat(3, object.getprixBiere());
            callableStatement.setInt(4, object.getVolumnBiere());
            callableStatement.setFloat(5, object.getTitrageBiere());
            callableStatement.setInt(6, object.getStockBiere());
            callableStatement.setInt(7, object.getIdType());
            callableStatement.setString(8, object.getnomType());
            callableStatement.setInt(9, object.getIdCouleur());
            callableStatement.setString(10, object.getnomCouleur());
            callableStatement.setInt(11, object.getIdMarque());
            callableStatement.setString(12, object.getnomMarque());
            callableStatement.setInt(13, object.getIdFabricant());
            callableStatement.setString(14, object.getnomFabricant());
            callableStatement.setInt(15, object.getIdPays());
            callableStatement.setString(16, object.getnomPays());
            callableStatement.setInt(17, object.getIdContinent());
            callableStatement.setString(18, object.getnomContinent());

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Article updatedArticle) {
        String updateQuery = "{call ps_UpdateArticle(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)}";
        try (CallableStatement callableStatement = connection.prepareCall(updateQuery))
           {
                callableStatement.setInt(1, updatedArticle.getIdArticle());
                callableStatement.setString(2, updatedArticle.getNomBiere());
                callableStatement.setFloat(3, updatedArticle.getprixBiere());
                callableStatement.setInt(4, updatedArticle.getVolumnBiere());
                callableStatement.setFloat(5, updatedArticle.getTitrageBiere());
                callableStatement.setInt(6, updatedArticle.getStockBiere());
                callableStatement.setInt(7, updatedArticle.getIdType());
                callableStatement.setString(8, updatedArticle.getnomType());
                callableStatement.setInt(9, updatedArticle.getIdCouleur());
                callableStatement.setString(10, updatedArticle.getnomCouleur());
                callableStatement.setInt(11, updatedArticle.getIdMarque());
                callableStatement.setString(12, updatedArticle.getnomMarque());
                callableStatement.setInt(13, updatedArticle.getIdFabricant());
                callableStatement.setString(14, updatedArticle.getnomFabricant());
                callableStatement.setInt(15, updatedArticle.getIdPays());
                callableStatement.setString(16, updatedArticle.getnomPays());
                callableStatement.setInt(17, updatedArticle.getIdContinent());
                callableStatement.setString(18, updatedArticle.getnomContinent());

                int rowsAffected = callableStatement.executeUpdate();

                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }


    @Override
    public boolean delete(Article object) {
        String sqlRequest = "Delete from Article WHERE ID_BIERE = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setInt(1, object.getIdArticle());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException E) {
            return false;
        }
    }


}
