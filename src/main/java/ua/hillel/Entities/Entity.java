package ua.hillel.Entities;

public abstract class Entity {

   public Long id;
    public String name;


    public Entity(Long id, String name) {
        this.id = id;
        this.name = name;

    }
}
