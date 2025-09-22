import dao.BD;
import dao.impl.DentistDAOH2;
import model.Dentist;
import service.DentistService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        DentistService dentistService = new DentistService(new DentistDAOH2());

        //Creamos las tablas
        BD.createTable();

        // crear algunos objeetos
        Dentist dentist1 = new Dentist(1234,"Martin", "Piccato");
        Dentist dentist2 = new Dentist(456,"Lautaro", "Piccato");
        Dentist dentist3 = new Dentist(789,"Lorenzo","Piccato");

        //persistir los objetos en la base de datos (guardarlos)
        dentistService.save(dentist1);
        dentistService.save(dentist2);
        dentistService.save(dentist3);

        // Consultar un odontólogo por id
        int id =2;
        dentistService.findByid(id);

        // Actualizar algún parámetro

        String updateName="Gabo";
        dentist2.setName(updateName);
        dentistService.update(dentist2);

        System.out.println("El nombre actualizado es: " + dentist2.getName());

        // Borrar un registro de la tabla
        int idDelete = 3;
        dentistService.delete(idDelete);

        //Consultar todos los registros
        dentistService.findAll();

    }
}