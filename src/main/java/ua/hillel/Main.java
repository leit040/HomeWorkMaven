package ua.hillel;
import ua.hillel.dao.LessonDao;
import ua.hillel.database.DataBaseConnection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataBaseConnection.init();

        LessonDao.deleteById(3L);
            LessonDao.findAll().forEach(entity ->{
            System.out.println(entity.id);
            System.out.println(entity.name);
            System.out.println(entity.homework.name);
            System.out.println("=============================================");
        });
    }
}
