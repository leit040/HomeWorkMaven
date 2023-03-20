package ua.hillel.dao;

import ua.hillel.Entities.Entity;


import java.sql.*;
import java.util.List;
import java.util.Optional;

public abstract class EntityDao  {

   public Connection connection = null;
   protected String tableName = null;




public EntityDao(Connection connection){
    this.connection = connection;

}

    public ResultSet findAll(String tableName) throws SQLException {
        PreparedStatement ps =  connection.prepareStatement("Select * from "+tableName);
        return  ps.executeQuery();

    }

    public ResultSet findById(Long id,String tableName) throws SQLException {
        PreparedStatement ps =  connection.prepareStatement("Select * from ? where id = ?");
        ps.setString(1,tableName);
        ps.setLong(2,id);
        return  ps.executeQuery();
    }

    public Entity save(Entity entity) {

        return entity;
    }

    public void update(Entity entity) {

    }

    public void deleteById(Long id){

    }

    public void delete(Entity entity){

    }
}
