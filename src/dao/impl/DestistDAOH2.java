package dao.impl;

import dao.IDao;
import model.Dentist;

import java.util.List;

public class DestistDAOH2 implements IDao<Dentist> {
    @Override
    public Dentist save(Dentist dentist) {
        return null;
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
