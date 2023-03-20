package ua.hillel.dao;

import ua.hillel.Entities.Entity;
import ua.hillel.Entities.HomeworkEntity;
import ua.hillel.database.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HomeworkDao{
    static Connection connection;

    static {
        try {
            connection = DataBaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<HomeworkEntity> findAll() throws SQLException {
        PreparedStatement ps =  connection.prepareStatement("Select * from homeworks");
        ResultSet rs =  ps.executeQuery();
        List<HomeworkEntity> result = new  ArrayList<>();
        while (rs.next()){
            result.add(extract(rs));
        }
        return result;
    }
    private static HomeworkEntity extract(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        String description = rs.getString("description");

        return new HomeworkEntity(id, name,description);
    }
    public static Optional<HomeworkEntity> findById(Long id) throws SQLException {
        PreparedStatement ps =  connection.prepareStatement("Select * from homeworks where id = ?");
        ps.setLong(1,id);
        ResultSet rs =  ps.executeQuery();
        if(!rs.first()){
            return Optional.empty();
        }
        return Optional.of(extract(rs));

    }

    public static HomeworkEntity save(HomeworkEntity entity) throws SQLException {
        if(findById(entity.id).isPresent()){
            PreparedStatement ps =  connection.prepareStatement("UPDATE  homeworks  SET name = ?, description = ? WHERE id = ?");
            ps.setString(1,entity.name);
            ps.setString(2,entity.description);
            ps.setLong(3,entity.id);
            ps.executeUpdate();
            return entity;
        }

        PreparedStatement ps =  connection.prepareStatement("INSERT INTO  homeworks  SET name = ?, description = ?");
        ps.setString(1,entity.name);
        ps.setString(2,entity.description);
        ps.executeQuery();
        entity.id = ps.getGeneratedKeys().getLong("id");
        return entity;
    }


    public static void deleteById(Long id) throws SQLException {
        PreparedStatement ps =  connection.prepareStatement("DELETE FROM homeworks WHERE id = ?");
        ps.setLong(1,id);
    }

    public static void delete(Entity entity) throws SQLException {
    deleteById(entity.id);
    }

}
