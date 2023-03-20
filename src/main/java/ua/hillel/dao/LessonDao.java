package ua.hillel.dao;


import ua.hillel.Entities.Entity;
import ua.hillel.Entities.LessonEntity;
import ua.hillel.database.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LessonDao {

    static Connection connection;

    static {
        try {
            connection = DataBaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<LessonEntity> findAll() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("Select * from lessons");
        ResultSet rs = ps.executeQuery();
        List<LessonEntity> result = new ArrayList<>();
        while (rs.next()) {
            result.add(extract(rs));
        }
        return result;
    }

    private static LessonEntity extract(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        Long homework_id = rs.getLong("homework_id");
        Optional hw = HomeworkDao.findById(homework_id);
        return new LessonEntity(id, name, hw);
    }

    public static Optional<LessonEntity> findById(Long id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("Select * from lessons where id = ?");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (!rs.first()) {
            return Optional.empty();
        }
        return Optional.of(extract(rs));

    }

    public static LessonEntity save(LessonEntity entity) throws SQLException {
        Long homework_id = HomeworkDao.save(entity.homework).id;
        if (findById(entity.id).isPresent()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE  lessons  SET name = ?,homework_id = ?  WHERE id = ?");
            ps.setString(1, entity.name);
            ps.setLong(2, homework_id);
            ps.setLong(3, entity.id);
            ps.executeUpdate();

            return entity;
        }
        PreparedStatement ps = connection.prepareStatement("INSERT INTO  lessons  SET name = ?,homework_id = ?");
        ps.setString(1, entity.name);
        ps.setLong(2, homework_id);

        ps.execute();
        entity.id = ps.getGeneratedKeys().getLong("id");

        return entity;
    }


    public static void deleteById(Long id) throws SQLException {
        Long homework_id = findById(id).get().homework.id;
        PreparedStatement ps = connection.prepareStatement("DELETE FROM lessons WHERE id = ?");
        ps.setLong(1, id);
        ps.execute();
        HomeworkDao.deleteById(homework_id);

    }

    public static void delete(Entity entity) throws SQLException {
        deleteById(entity.id);
    }


}
