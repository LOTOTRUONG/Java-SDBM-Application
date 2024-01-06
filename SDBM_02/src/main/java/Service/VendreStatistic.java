package Service;

import DAO.SDBMConnection;
import DAO.VendreDAO;
import Metier.Vendre;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendreStatistic {
    VendreDAO vendreDAO = new VendreDAO();
    private final List<Vendre> vendreList = vendreDAO.getAllVendre();

    public VendreStatistic() {

    }

    public Map<String, Map<Integer, Integer>> getQuantiteByAnneeAndCouleur() {
        Map<String, Map<Integer, Integer>> data = new HashMap<>();
        String sqlRequest = "{call ps_VendreParAnneeCouleur}";
        try (Connection connection = SDBMConnection.getInstance();
             CallableStatement callableStatement = connection.prepareCall(sqlRequest)) {
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String color = resultSet.getString("NOM_COULEUR");
                Map<Integer, Integer> yearData = data.computeIfAbsent(color, k -> new HashMap<>());

                for (int year : new int[]{2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022}) {
                    int totalQuantity = resultSet.getInt(String.valueOf(year));
                    yearData.put(year, totalQuantity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }





    public Map<String, Map<Integer, Integer>> getQuantiteByAnneeAndType() {
        Map<String, Map<Integer, Integer>> data = new HashMap<>();
        String sqlRequest = "{call ps_VendreParAnneeType}";
        try (Connection connection = SDBMConnection.getInstance();
             CallableStatement callableStatement = connection.prepareCall(sqlRequest)) {
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String color = resultSet.getString("NOM_TYPE");
                Map<Integer, Integer> yearData = data.computeIfAbsent(color, k -> new HashMap<>());

                for (int year : new int[]{2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022}) {
                    int totalQuantity = resultSet.getInt(String.valueOf(year));
                    yearData.put(year, totalQuantity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


    public Map<String, Map<Integer, Integer>> getQuantiteByAnneeAndContinent() {
        Map<String, Map<Integer, Integer>> data = new HashMap<>();
        String sqlRequest = "{call ps_VendreParAnneeContinent}";
        try (Connection connection = SDBMConnection.getInstance();
             CallableStatement callableStatement = connection.prepareCall(sqlRequest)) {
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String color = resultSet.getString("NOM_CONTINENT");
                Map<Integer, Integer> yearData = data.computeIfAbsent(color, k -> new HashMap<>());

                for (int year : new int[]{2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022}) {
                    int totalQuantity = resultSet.getInt(String.valueOf(year));
                    yearData.put(year, totalQuantity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }



}
