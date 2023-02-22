package alishev.spring.dao;

import alishev.spring.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM book ORDER BY title",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getBookById(long id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public List<Book> getBooksByPeronId(long personId) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?", new Object[]{personId},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void createBook(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, year_book) VALUES(?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYearBook());
    }

    public void updateBook(long id, Book book) {
        jdbcTemplate.update("UPDATE book SET title=?, author=?, year_book=? WHERE id=?",
                book.getTitle(), book.getAuthor(), book.getYearBook(), id);
    }

    public void updateOwnerBook(long id, long personId) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE id=?",
                personId, id);
    }

    public void updateFreeOwnerBook(long id) {
        jdbcTemplate.update("UPDATE book SET person_id=null WHERE id=?", id);
    }

    public void deleteBook(long id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }
}

