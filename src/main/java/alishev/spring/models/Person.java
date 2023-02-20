package alishev.spring.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class Person {
    private long id;
    @NotEmpty(message = "Name should not be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+, [A-Z]\\w+", message = "NAME Format will be: Firstname, Secondname, Lastname")
    private String name;
    @NotEmpty(message = "Year should not be empty")
    private int year;
    private List<Book> books;


    public Person() {
    }

    public Person(long id, String name, int year, List<Book> books) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
