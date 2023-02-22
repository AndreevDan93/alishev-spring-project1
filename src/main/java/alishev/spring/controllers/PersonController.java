package alishev.spring.controllers;

import alishev.spring.dao.BookDAO;
import alishev.spring.dao.PersonDAO;
import alishev.spring.models.Book;
import alishev.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @Autowired
    public PersonController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

//    Получить людей
    @GetMapping()
    public String getPeople(Model model) {
        model.addAttribute("people", personDAO.getPeople());
        return "people/people";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") long id,
                            Model model) {
        Person person = personDAO.getPersonById(id);
        List<Book> books = bookDAO.getBooksByPeronId(id);
        model.addAttribute("person", person);
        model.addAttribute("books", books);
        return "people/show";

    }

//    Создание пользователя
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping("")
    public String createPerson(@ModelAttribute("person") Person person) {
        personDAO.createPerson(person);
        return "redirect:/people";
    }

//    Редактирование человека
    @GetMapping("/{id}/edit")
    public String editPerson(Model model,
                             @PathVariable("id") long id) {
        model.addAttribute("person", personDAO.getPersonById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") Person person,
                               @PathVariable("id") long id) {
        personDAO.updatePerson(id, person);
        return "redirect:/people";
    }

//    Удаление человека
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") long id) {
        personDAO.deletePerson(id);
        return "redirect:/people";
    }

}
