package alishev.spring.models;

public class Book {
    private long id;

    private long personId;

    private String name;

    private String autor;

    private int year;

    public Book() {
    }

    public Book(long id, long personId, String name, String autor, int year) {
        this.id = id;
        this.personId = personId;
        this.name = name;
        this.autor = autor;
        this.year = year;
    }
}
