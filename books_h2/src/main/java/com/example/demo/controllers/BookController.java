package com.example.demo.controllers;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public Iterable<Book> getAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Map<String, Object> getById(@PathVariable Integer id) {
        Optional<Book> result = bookRepository.findById(id);
        Map<String, Object> return_values = new HashMap<>();
        return_values.put("result" ,  result.isPresent() ? result.get() : "id " + id + " does not exist.");
        return return_values;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        bookRepository.deleteById(id);
    }
}
