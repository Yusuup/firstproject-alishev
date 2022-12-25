package ru.yusupov.firstproject.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

public class Person {
    private int id;

    @NotEmpty(message = "Заполните поле")
    @Size(min = 2, max = 100, message = "Кол-во символов должно быть от 2 до 100")
    @Pattern(regexp = "[\\u0410-\\u042f][\\u0430-\\u044f]+ [\\u0410-\\u042f][\\u0430-\\u044f]+ [\\u0410-\\u042f][\\u0430-\\u044f]+"
    , message = "Введите правильно ФИО по Формату: Фамилия Имя Отчество")
    private String fullName;

    @Min(value = 1899)
    @Max(value = 2022)
    private int yearBirth;

    public Person() {
    }

    public Person(int id, String fullName, int yearBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearBirth = yearBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }
}
