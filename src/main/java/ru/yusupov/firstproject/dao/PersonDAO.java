package ru.yusupov.firstproject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yusupov.firstproject.models.Book;
import ru.yusupov.firstproject.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("select * from person where id = ?", new BeanPropertyRowMapper<>(Person.class), new Object[]{id}).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person (fullname, yearbirth) values (?, ?)", person.getFullName(), person.getYearBirth());
    }

    public void update(int id, Person upPerson) {
        jdbcTemplate.update("update person set fullname = ?, yearbirth = ? where id = ?",
                upPerson.getFullName(), upPerson.getYearBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from person where id = ?", id);
    }

    public List<Book> bookList(int id) {
        return jdbcTemplate.query("select * from book where person_id = ?", new BeanPropertyRowMapper<>(Book.class), id);
    }
}
