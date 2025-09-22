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
        return null;
    }

    @Override
    public void update(Dentist dentist) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Dentist> findAll() {
        return List.of();
    }
}
