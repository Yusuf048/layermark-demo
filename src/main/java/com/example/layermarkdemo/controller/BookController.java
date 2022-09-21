package com.example.layermarkdemo.controller;

import com.example.layermarkdemo.model.Book;
import com.example.layermarkdemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("")
    public List<Book> getBooks(){
        return this.bookRepository.findAll();
    }

    @PostMapping("")
    public Book addBook(@RequestBody Book book){
        return this.bookRepository.save(book);
    }

    @PutMapping("/{book_id}")
    public Book updateBook(@PathVariable("book_id") Long book_id, @RequestBody Book book){
        Book foundBook = bookRepository.findById(book_id).orElse(null);
        if (foundBook == null)
        {
            // Throw exception
        }
        if (book.getBookName() != null && foundBook.getBookName().equals(book.getBookName()))
        {
            foundBook.setBookName(book.getBookName());
        }
        // TODO
        return this.bookRepository.save(foundBook);
    }

    /*@DeleteMapping("{book_id}")
    public List<Book> deleteBook(){
        return this.bookRepository.save();
    }*/

    /*@GetMapping("books/{author}")
    public List<Book> getBooks(){
        return this.bookRepository.findAll();
    }*/

}
