package ru.yusupov.firstproject.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    int id;

    @Size(min = 2, max = 100, message = "Кол-во символов названии должно быть от 2 до 100")
    String name;

    @Size(min = 2, max = 100, message = "Кол-во символов должно быть от 2 до 100")
    String author;

    @Min(value = 1800, message = "Введите год, который начинается с 1800")
    @Max(value = 2022, message = "Введите год, который заканчивается 2022")
    int year;

    public Book(){}

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}