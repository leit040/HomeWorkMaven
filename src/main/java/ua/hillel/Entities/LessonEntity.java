package ua.hillel.Entities;

import java.util.Optional;

public class LessonEntity extends Entity{

    public HomeworkEntity homework = null;

    public LessonEntity(Long id, String name, Optional homework) {
        super(id, name);
    this.homework = (HomeworkEntity) homework.get();
    }
}
