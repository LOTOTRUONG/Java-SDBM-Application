package DAO;

import Metier.Continent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ContinentDAO extends DAO<Continent, Continent, Integer> {
    @Override
    public Continent getByID(Integer id) {
        String sqlRequest = "Select id_continent, nom_continent from continent where id_continent = " + id;
        Continent continent;
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            if(resultSet.next()) return new Continent(resultSet.getInt(1),resultSet.getString(2));
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Continent getByLibelle(String libelle) {
        String sqlRequest = "Select id_continent, nom_continent from continent where nom_continent = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setString(1, libelle);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) return new Continent(resultSet.getInt(1),resultSet.getString(2));
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

public ArrayList<Continent> getAll(){
        ArrayList<Continent> listContinent = new ArrayList<>();
        String sqlRequest = "SELECT ID_CONTINENT, NOM_CONTINENT FROM CONTINENT";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            while (resultSet.next()) {
                listContinent.add(new Continent(resultSet.getInt(1),resultSet.getString(2)));
            } resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listContinent;
}

@Override
    public ArrayList<Continent> getLike(Continent object) {
        String sqlCommand = "SELECT ID_CONTINENT, NOM_CONTINENT FROM CONTINENT WHERE NOM_CONTINENT LIKE '%" + object.getNomContinent()+"%'";
        ArrayList<Continent> listContinent = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                listContinent.add(new Continent(resultSet.getInt(1),resultSet.getString(2)));
            } resultSet.close();
        } catch (Exception e) {e.printStackTrace();}
        return listContinent;
}
    @Override
    public boolean update(Continent continent) {
        String sqlRequest = "update CONTINENT set NOM_CONTINENT = ? WHERE ID_CONTINENT = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest,Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1,continent.getNomContinent());
            preparedStatement.setInt(2, continent.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException E) {
            E.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean insert(Continent continent) {
        String sqlRequest = "insert into CONTINENT values " + continent.getNomContinent();
        try(Statement statement = connection.createStatement()) {
            statement.execute(sqlRequest);
            return true;
        }catch (SQLException E) {
            E.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean delete(Continent object) {
        String sqlRequest = "Delete from CONTINENT WHERE ID_CONTINENT = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setInt(1, object.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException E) {
            return false;
        }
    }



}
