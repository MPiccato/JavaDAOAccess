package dao.impl;

import dao.BD;
import dao.IDao;
import model.Dentist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class DentistDAOH2 implements IDao<Dentist> {


    private static final String SQL_INSERT = "INSERT INTO DENTIST VALUES (?,?,?);";

    private static final String SQL_SEARCH_BY_ID = "SELECT * FROM DENTIST WHERE ID = ?";

    private static final String SQL_UPDATE = "UPDATE DENTIST SET REGISTRATION = ?, NAME = ?,LASTNAME = ? WHERE ID = ?;";

    private static final String SQL_DELETE = "DELETE * FROM DENTIST WHERE ID = ?";



    @Override
    public Dentist save(Dentist dentist) {
        Connection connection = null;



        try {

            connection = BD.getConnection();

            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            psInsert.setInt(1, dentist.getRegistration());
            psInsert.setString(2,dentist.getName());
            psInsert.setString(3,dentist.getLastName());
            psInsert.execute();

            ResultSet rs = psInsert.getGeneratedKeys();

            while (rs.next()) {
                dentist.setId(rs.getInt(1)); //Recupero el ID generado
                System.out.println("Este es el odontólogo que se guardó: " + dentist);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dentist;
    }

    @Override
    public Dentist findById(Integer id) {
       Connection connection = null;



       Dentist dentist = null;

       try {
           connection = BD.getConnection();

           PreparedStatement psFindById = connection.prepareStatement(SQL_SEARCH_BY_ID, Statement.RETURN_GENERATED_KEYS);

           psFindById.setInt(1, id);
           psFindById.execute();

           ResultSet rsSearch = psFindById.executeQuery();

           while (rsSearch.next()) {
               dentist = new Dentist(rsSearch.getInt(1),
                       rsSearch.getInt(2),rsSearch.getString(3),
                       rsSearch.getString(4));
               System.out.println("Consultamos el odontólogo con id: " + rsSearch.getInt(1)
                                    +", Nombre: " + rsSearch.getString(3) +
                       "  Apellido: " + rsSearch.getString(4));
           }
           } catch (Exception e) {
               e.printStackTrace();
           } finally {
               try {
                   connection.close();

               } catch (Exception e) {
                   e.printStackTrace();
               }


       }
       return dentist;
    }

    @Override
    public void update(Dentist dentist) {
        Connection connection = null;


        try {
            connection = BD.getConnection();
            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE);

            psUpdate.setInt(1,dentist.getRegistration());
            psUpdate.setString(2,dentist.getName());
            psUpdate.setString(3,dentist.getLastName());
            psUpdate.setInt(4,dentist.getId());
            psUpdate.execute();

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

    @Override
    public void delete(Integer id) {

        Connection connection = null;

        try {

            connection = BD.getConnection();

            PreparedStatement psDelete = connection.prepareStatement(SQL_DELETE);

            psDelete.setInt(1,id);
            psDelete.execute();

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

    @Override
    public List<Dentist> findAll() {
        return List.of();
    }
}
