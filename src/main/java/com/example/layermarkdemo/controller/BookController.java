package com.example.layermarkdemo.controller;

import com.example.layermarkdemo.model.Book;
import com.example.layermarkdemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

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

    // Get all books by genre
    @GetMapping("/{genre}/{start}/{end}")
    public List<Book> getBooksByGenre(@PathVariable("genre") String genre, @PathVariable("start")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start, @PathVariable("end")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){
        String[] genres = {"Action and Adventure", "Fantasy", "Historical", "Science Fiction", "Thriller", "Classics", "Historical Fiction", "Mystery", "Romance", "Biography"};
        List<String> genre_list = Arrays.asList(genres);

        if(genre_list.contains(genre)){
            if (bookRepository.findByGenre(genre).isPresent() && bookRepository.findByReleaseDateAfter(start).isPresent() && bookRepository.findByReleaseDateBefore(end).isPresent()){
                List<Book> booksOfGenre =  this.bookRepository.findByGenre(genre).get();
                List<Book> booksOfDates =  this.bookRepository.findByReleaseDateBetween(start, end).get();
                List<Book> combinedBooks = new ArrayList<Book>();

                for (Book t : booksOfGenre) {
                    if(booksOfDates.contains(t)) {
                        combinedBooks.add(t);
                    }
                }
                return combinedBooks;
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
    }

    // Get filtered books by genre
    @GetMapping("/{genre}/{start}/{end}/{author}/{bookName}")
    public List<Book> getBooksByGenreFiltered(@PathVariable("genre") String genre,@PathVariable("start")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start, @PathVariable("end")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end, @PathVariable("author") String author, @PathVariable("bookName") String bookName){
        String[] genres = {"Action and Adventure", "Fantasy", "Historical", "Science Fiction", "Thriller", "Classics", "Historical Fiction", "Mystery", "Romance"};
        List<String> genre_list = Arrays.asList(genres);

        // Search if genre exists
        if(genre_list.contains(genre)){
            if(author.equals("Author Name") && bookName.equals("Book Name")){
                // Find books by genre and release date then combine the lists of books
                if (bookRepository.findByGenre(genre).isPresent() && bookRepository.findByReleaseDateBetween(start, end).isPresent()){
                    List<Book> booksOfGenre =  this.bookRepository.findByGenre(genre).get();
                    List<Book> booksOfDates =  this.bookRepository.findByReleaseDateBetween(start, end).get();
                    List<Book> combinedBooks = new ArrayList<Book>();

                    for (Book t : booksOfGenre) {
                        if(booksOfDates.contains(t)) {
                            combinedBooks.add(t);
                        }
                    }
                    return combinedBooks;
                }
            } else if (author.equals("Author Name")){
                if (bookRepository.findByGenre(genre).isPresent() && bookRepository.findByReleaseDateBetween(start, end).isPresent()){
                    List<Book> booksOfGenre =  this.bookRepository.findByGenre(genre).get();
                    List<Book> booksOfDates =  this.bookRepository.findByReleaseDateBetween(start, end).get();
                    List<Book> combinedBooks = new ArrayList<Book>();

                    for (Book t : booksOfGenre) {
                        if(t.getBookName().equals(bookName)){
                            if(booksOfDates.contains(t)) {
                                combinedBooks.add(t);
                            }
                        }
                    }
                    return combinedBooks;
                }
            } else if (bookName.equals("Book Name")){
                if (bookRepository.findByGenre(genre).isPresent() && bookRepository.findByReleaseDateBetween(start, end).isPresent()){
                    List<Book> booksOfGenre =  this.bookRepository.findByGenre(genre).get();
                    List<Book> booksOfDates =  this.bookRepository.findByReleaseDateBetween(start, end).get();
                    List<Book> combinedBooks = new ArrayList<Book>();

                    for (Book t : booksOfGenre) {
                        if(t.getAuthor().equals(author)){
                            if(booksOfDates.contains(t)) {
                                combinedBooks.add(t);
                            }
                        }
                    }
                    return combinedBooks;
                }
            } else {
                if (bookRepository.findByGenre(genre).isPresent() && bookRepository.findByReleaseDateBetween(start, end).isPresent()){
                    List<Book> booksOfGenre =  this.bookRepository.findByGenre(genre).get();
                    List<Book> booksOfDates =  this.bookRepository.findByReleaseDateBetween(start, end).get();
                    List<Book> combinedBooks = new ArrayList<Book>();

                    for (Book t : booksOfGenre) {
                        if(t.getAuthor().equals(author) && t.getBookName().equals(bookName)){
                            if(booksOfDates.contains(t)) {
                                combinedBooks.add(t);
                            }
                        }
                    }
                    return combinedBooks;
                }
            }
        } else {
            try {
                genre_list.contains(genre);
            } catch (NullPointerException e) {
                throw new NullPointerException("The genre does not exist");
            }
        }
        return Collections.emptyList();
    }

    // Add Book
    @PostMapping("")
    public Book addBook(@RequestBody Book book){
        if (book.getImgUrl() == null) {
            book.setImgUrl("https://static.vecteezy.com/system/resources/previews/002/398/513/original/blank-book-cover-free-vector.jpg");
        }
        return this.bookRepository.save(book);
    }

    // Edit Book
    @PutMapping("/{book_id}")
    public Book updateBook(@PathVariable("book_id") Long book_id, @RequestBody Book book){
        Book foundBook = bookRepository.findById(book_id).orElse(null);
        if (!("".equals(book.getBookName())))
        {
            foundBook.setBookName(book.getBookName());
        }
        if (!("".equals(book.getImgUrl()))) {
            foundBook.setImgUrl(book.getImgUrl());
        }
        if (!("".equals(book.getAuthor()))) {
            foundBook.setAuthor(book.getAuthor());
        }
        if (!("".equals(book.getReleaseDate()))) {
            foundBook.setReleaseDate(book.getReleaseDate());
        }
        if (!("".equals(book.getGenre()))) {
            foundBook.setGenre(book.getGenre());
        }
        return this.bookRepository.save(foundBook);
    }

    // Delete Book
    @DeleteMapping("/{book_id}")
    public List<Book> deleteBook(@PathVariable("book_id") Long book_id){
        this.bookRepository.deleteById(book_id);
        return this.bookRepository.findAll();
    }

}
