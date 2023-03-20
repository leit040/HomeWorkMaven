package ua.hillel.Entities;

public class HomeworkEntity extends Entity{

    public String description = null;

    public HomeworkEntity(Long id, String name,String description) {
        super(id, name);
        this.description = description;
    }
}
