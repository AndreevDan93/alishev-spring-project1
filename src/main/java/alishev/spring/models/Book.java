package alishev.spring.models;

import javax.validation.constraints.NotEmpty;

public class Book {
    private long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String author;
    @NotEmpty
    private int yearBook;
    private Long personId;

    public Book() {

    }

    public Book(String title, String author, int yearBook) {
        this.title = title;
        this.author = author;
        this.yearBook = yearBook;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearBook() {
        return yearBook;
    }

    public void setYearBook(int yearBook) {
        this.yearBook = yearBook;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}
