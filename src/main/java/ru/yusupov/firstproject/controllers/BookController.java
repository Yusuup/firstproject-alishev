package ru.yusupov.firstproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.yusupov.firstproject.dao.BookDAO;
import ru.yusupov.firstproject.dao.PersonDAO;
import ru.yusupov.firstproject.models.Book;
import ru.yusupov.firstproject.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/book")
public class BookController {
    private BookDAO bookDAO;
    private PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("book", bookDAO.index());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person1") Person person) {
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("persons", personDAO.index());
        model.addAttribute("person", bookDAO.getUser(id));
//        System.out.println(bookDAO.getUser(id).);
        return "book/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "book/new";
        bookDAO.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) return "book/edit";
        bookDAO.update(id, book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/add")
    public String add(@PathVariable("id") int id, @ModelAttribute("person1") Person person) {
        bookDAO.updatePerson(id, person);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        bookDAO.deleteUser(id);
        return "redirect:/book";
    }
}
