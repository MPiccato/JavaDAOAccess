package test;

import dao.BD;
import dao.impl.DentistDAOH2;
import model.Dentist;
import org.junit.jupiter.api.Test;
import service.DentistService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DentistServiceTest {

    DentistService dentistService = new DentistService(new DentistDAOH2());

    @Test
    void save() {
        BD.createTable();
        Dentist dentist = new Dentist();
        dentist.setName("Pedro");
        dentist.setLastName("Pascal");
        dentist.setRegistration(234567899);

        dentistService.save(dentist);

        assertNotNull(dentist.getId());
    }

    @Test
    void findByid() {
        Dentist dentist = dentistService.findByid(1);
        assertNotNull(dentist.getId());
    }

    @Test
    void update() {
        Dentist dUpdate = new Dentist();
        dUpdate.setId(1);
        dUpdate.setName("Alfonso");
        dUpdate.setLastName("Pascal");
        dUpdate.setRegistration(46965);

        dentistService.update(dUpdate);
        assertNotNull(dUpdate.getId());
        assertEquals(true,dUpdate.getName().equals("Alfonso"));


    }

    @Test
    void delete() {
        Dentist dentistDeleted = dentistService.findByid(3);
        assertNull(dentistDeleted);
    }

    @org.junit.jupiter.api.Test
    void findAll() {
        List<Dentist> dentistList = dentistService.findAll();
        assertTrue(dentistList.size() > 0);
    }
}