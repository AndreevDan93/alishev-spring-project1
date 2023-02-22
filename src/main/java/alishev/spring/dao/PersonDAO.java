package alishev.spring.dao;

import alishev.spring.models.Book;
import alishev.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    private final BookDAO bookDAO;


    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate, BookDAO bookDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookDAO = bookDAO;
    }

    public List<Person> getPeople() {
        return jdbcTemplate.query("SELECT * FROM person ORDER BY full_name",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPersonById(long id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public Person getOwnerByBook(long id) {
        Book book = bookDAO.getBookById(id);
        if (book.getPersonId() == null) {
            return null;
        }
        return getPersonById(book.getPersonId());

    }

    public void createPerson(Person person) {
        jdbcTemplate.update("INSERT INTO person(full_name, year_of_birth) VALUES(?, ?)",
                person.getFullName(), person.getYearOfBirth());
    }

    public void updatePerson(long id, Person person) {
        jdbcTemplate.update("UPDATE person SET full_name=?, year_of_birth=? WHERE id=?",
                person.getFullName(), person.getYearOfBirth(), id);
    }

    public void deletePerson(long id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }




}
