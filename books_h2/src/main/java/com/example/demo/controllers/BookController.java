package com.example.demo.controllers;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    // 2. Autowired
    //    @Autowired
    //    private BookRepository bookRepository;

    // 1. ctor gets the repo
    private final BookRepository bookRepository;
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/jdbc")
    public Iterable<Book> getAllJdbc() {
        String query = "SELECT * FROM BOOK";
        List<Book> result = jdbcTemplate.query(query, new BookRowMapper());
        return result;
    }

    private static class BookRowMapper implements RowMapper<Book>  {
        @Override
        // id  title pages author
        //  1   james  128  bond
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Integer id = rs.getInt("id");
            String title = rs.getString("title");
            Integer pages = rs.getInt("pages");
            String author = rs.getString("Author");
            Book book = new Book(id, title, pages, author);
            return book;
        }
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
