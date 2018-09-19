package com.zl.springdemo;

import java.util.List;

public class Classes {

    private int id;
    private String name;
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Classes " + "id=" + id + ",name="+name + ", students=" + students;
    }
}
