package com.example.rajdeeprao.xmlparsingdemo;

/**
 * Created by rajdeeprao on 2/13/17.
 */

public class Person {
    String name;
    String department;
    String age;
    String Id;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", age='" + age + '\'' +
                ", Id='" + Id + '\'' +
                '}';
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
