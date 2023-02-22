package alishev.spring.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Person {
    private long id;
    @NotEmpty(message = "Name should not be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+, [A-Z]\\w+", message = "NAME Format will be: Firstname, Secondname, Lastname")
    private String fullName;
    @NotEmpty(message = "Year should not be empty")
    private int yearOfBirth;


    public Person() {
    }

    public Person(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

}
