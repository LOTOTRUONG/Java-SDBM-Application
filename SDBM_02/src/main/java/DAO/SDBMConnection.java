package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SDBMConnection {
    private static Connection connection;
    private SDBMConnection(){

    }
    public static Connection getInstance(){

            try {
                String dbURL = "jdbc:sqlserver://localhost:1433; database = SDBM";
                String user = "dev";
                String password = "loan@123456";
                connection = DriverManager.getConnection(dbURL, user, password);
               // if (connection != null) {
                //    DatabaseMetaData databaseMetaData = (DatabaseMetaData) connection.getMetaData();
               //     System.out.println("Driver name: " + databaseMetaData.getDriverName());
                //    System.out.println("Driver version: " + databaseMetaData.getDriverVersion());
                //     System.out.println("Product version: " + databaseMetaData.getDatabaseProductVersion());
               // }
                return connection;
            } catch (SQLException exception) {
                exception.printStackTrace();
                return null;
            }
    }
}
