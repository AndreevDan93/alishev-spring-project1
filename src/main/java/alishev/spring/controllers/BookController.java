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
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

//    Получение книг
    @GetMapping()
    public String getBooks(Model model) {
        model.addAttribute("books", bookDAO.getBooks());
        return "books/books";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") long id,
                          Model model,
                          @ModelAttribute("person") Person person) {
        Book book = bookDAO.getBookById(id);
        List<Person> people = personDAO.getPeople();
        Person owner = personDAO.getOwnerByBookId(id);
        model.addAttribute("owner", owner);
        model.addAttribute("book", book);
        model.addAttribute("people", people);
        return "books/show";
    }

//    Создать книгу
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping("")
    public String createBook(@ModelAttribute("book") Book book) {
        bookDAO.createBook(book);
        return "redirect:/books";
    }
//      Редактировать книгу
    @GetMapping("/{id}/edit")
    public String editBook(Model model,
                           @PathVariable("id") long id) {
        model.addAttribute("book", bookDAO.getBookById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book book,
                             @PathVariable("id") long id) {
        bookDAO.updateBook(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String takeBook(@ModelAttribute("person") Person person,
                           @PathVariable("id") long id) {
        if (bookDAO.getBookById(id).getPersonId() == null) {
            bookDAO.updateOwnerBook(id, person.getId());
        } else {
            bookDAO.updateFreeOwnerBook(id);
        }
        return "redirect:/books";
    }

//    Удалить книгу
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") long id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }



}
