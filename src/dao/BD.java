package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BD {

    private static final String  SQL_CREATE_TABLE_DENTIST = "DROP TABLE IF EXISTS;  " +
            "CREATE TABLE DENTIST " +
            "(" +
            "ID INT AUTOINCREMENT NOT NULL, " +
            "REGISTRATION INT NOT NULL," +
            "NAME VARCHAR(100) NOT NULL," +
            "LASTNAME VARCHAR(100) NOT NULL" +
            ");";

    private static final String SQL_CREATE_TABLE_PATIENT = "DROP TABLE IF EXISTS; " +
            "CREATE TABLE PATIENT "
            + "("
            + "ID INT AUTOINCREMENT NOT NULL,"
            + "NAME VARCHAR(100) NOT NULL,"
            + "LASTNAME VARCHAR(100) NOT NULL,"
            + "CARDIDENTITY VARCHAR(100) NOT NULL,"
            + "ADMISSIONOFDATE DATE NOT NULL"
            + ");";

    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/ClinicaOdontologicaDAO");
    }

    public static void createTable() {



        Connection connection = null;

        try {

            connection = getConnection();

            // Creo las tablas de dentista y paciente
            Statement statement = connection.createStatement();
            statement.execute(SQL_CREATE_TABLE_DENTIST);
            statement.execute(SQL_CREATE_TABLE_PATIENT);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
