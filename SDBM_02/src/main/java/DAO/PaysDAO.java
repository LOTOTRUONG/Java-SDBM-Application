package DAO;

import Metier.Continent;
import Metier.Pays;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaysDAO extends DAO<Pays, Pays, Integer> {
    @Override
    public Pays getByID(Integer id) {
        String sqlRequest = "Select id_pays, nom_pays from pays where id_pays = " +id;
        Pays pays;
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            if(resultSet.next()) return new Pays(resultSet.getInt(1),resultSet.getString(2));
            return null;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
    public Pays getByLibelle(String libelle) {
        String sqlRequest = "Select id_pays, nom_pays from PAYS where nom_pays = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setString(1, libelle);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) return new Pays(resultSet.getInt(1),resultSet.getString(2));
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public ArrayList<Pays> getAll() {
        ArrayList<Pays> liste = new ArrayList<>();
        String sqlRequest = "SELECT ID_Pays, NOM_Pays FROM Pays";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            while (resultSet.next()) {
                liste.add(new Pays(resultSet.getInt(1),resultSet.getString(2)));
            } resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Pays> getLike(Pays object) {
        String sqlCommand = "SELECT ID_Pays, NOM_Pays FROM Marque WHERE ID_Pays LIKE '%" + object.getLibelle()+"%'";
        ArrayList<Pays> liste = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                liste.add(new Pays(resultSet.getInt(1),resultSet.getString(2)));
            } resultSet.close();
        } catch (Exception e) {e.printStackTrace();}
        return liste;
    }

    @Override
    public boolean insert(Pays object) {
        String sqlRequest = "update PAYS set NOM_PAYS = ? WHERE ID_PAYS = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest,Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, object.getLibelle());
            preparedStatement.setInt(2, object.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException E) {
            E.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Pays object) {
        String sqlRequest = "insert into PAYS values " + object.getLibelle();
        try(Statement statement = connection.createStatement()) {
            statement.execute(sqlRequest);
            return true;
        }catch (SQLException E) {
            E.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Pays object) {
        String sqlRequest = "Delete from PAYS WHERE ID_PAYS = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setInt(1, object.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException E) {
            return false;
        }
    }

    public List<Pays> getByContinent(Continent continent) {
        List<Pays> paysList = new ArrayList<>();
        String sqlRequest = "SELECT id_pays, nom_pays FROM PAYS WHERE id_continent = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setInt(1, continent.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                paysList.add(new Pays(resultSet.getInt(1), resultSet.getString(2), continent));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paysList;
    }
}
