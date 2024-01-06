package DAO;

import Metier.Vendre;
import Metier.idTicket;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendreDAO {



    public List<Vendre> getAllVendre() {

        String sqlRequest = "Select * from VENDRE " +
                "JOIN ARTICLE on ARTICLE.ID_BIERE = VENDRE.ID_BIERE ";
        List<Vendre> vendreList = new ArrayList<>();
        try (Connection sdbmConnection = SDBMConnection.getInstance();
             CallableStatement callableStatement = sdbmConnection.prepareCall(sqlRequest)) {
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int annee = resultSet.getInt("annee");
                int numeroTicket = resultSet.getInt("numero_ticket");
                int id_article = resultSet.getInt("id_biere");
                int quantite = resultSet.getInt("quantite_de_vente");
                double prix_vendre = resultSet.getDouble("prix_de_vendre");

                idTicket idTicket = new idTicket(annee, numeroTicket);
                Vendre vendre = new Vendre(idTicket, id_article, quantite,prix_vendre);
                vendreList.add(vendre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendreList;

    }



}
