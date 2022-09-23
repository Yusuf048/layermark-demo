package com.example.layermarkdemo.controller;

import com.example.layermarkdemo.model.Book;
import com.example.layermarkdemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("book")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("")
    public List<Book> getBooks(){
        return this.bookRepository.findAll();
    }

    @GetMapping("/{genre}")
    public List<Book> getBooksByGenre(@PathVariable("genre") String genre){
        String[] genres = {"Action and Adventure", "Fantasy", "Historical", "Science Fiction", "Thriller", "Classics", "Historical Fiction", "Mystery", "Romance"};
        List<String> genre_list = Arrays.asList(genres);
        // HashMap<String, List<Book>> booksByGenre = new HashMap<>();

        if(genre_list.contains(genre)){
            if (bookRepository.findByGenre(genre).isPresent()){
                return this.bookRepository.findByGenre(genre).get();
            } else {
                return Collections.emptyList();
            }
        } else {
            try {
                genre_list.contains(genre);
            } catch (NullPointerException e) {
                throw new NullPointerException("The genre does not exist");
            }
        }
        return Collections.emptyList();

        /*for (String genre
                : genres
             ) {
            if (bookRepository.findByGenre(genre).isPresent()){
                booksByGenre.put(genre, bookRepository.findByGenre(genre).get());
            }

        }*/
    }

    @PostMapping("")
    public Book addBook(@RequestBody Book book){
        if (book.getImgUrl() == null) {
            book.setImgUrl("https://static.vecteezy.com/system/resources/previews/002/398/513/original/blank-book-cover-free-vector.jpg");
        }
        return this.bookRepository.save(book);
    }

    @PutMapping("/{book_id}")
    public Book updateBook(@PathVariable("book_id") Long book_id, @RequestBody Book book){
        Book foundBook = bookRepository.findById(book_id).orElse(null);
        if (foundBook == null)
        {
            // Throw exception
        }
        if (book.getBookName() != null)
        {
            foundBook.setBookName(book.getBookName());
        }
        if (book.getImgUrl() != null) {
            foundBook.setImgUrl(book.getImgUrl());
        }
        if (book.getAuthor() != null) {
            foundBook.setAuthor(book.getAuthor());
        }
        if (book.getReleaseDate() != null) {
            foundBook.setReleaseDate(book.getReleaseDate());
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
