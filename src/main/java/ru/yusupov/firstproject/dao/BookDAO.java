package ru.yusupov.firstproject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yusupov.firstproject.models.Book;
import ru.yusupov.firstproject.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("select * from book where id = ?", new BeanPropertyRowMapper<>(Book.class),
                new Object[]{id}).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into book(name, author, year) VALUES (?,?,?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book upBook) {
        jdbcTemplate.update("update book set name = ?, author = ?, year = ? where id = ?",
                upBook.getName(), upBook.getAuthor(), upBook.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from book where id = ?", id);
    }

    public Optional<Person> getUser(int id) {
        return jdbcTemplate.query("select person_id, fullname, yearbirth from person join book on person.id = book.person_id where book.id = ?",
                new BeanPropertyRowMapper<>(Person.class), new Object[]{id}).stream().findAny();
    }

    public void updatePerson(int id, Person person) {
        jdbcTemplate.update("update book set person_id = ? where id = ?", person.getId(), id);
    }

    public void deleteUser(int id) {
        jdbcTemplate.update("update book set person_id = null where id = ?", id);
    }
}
