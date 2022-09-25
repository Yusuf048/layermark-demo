package com.example.layermarkdemo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {
    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author")
    private String author;

    // LocalDate format: yyyy-MM-dd
    @Column(name = "release_date", columnDefinition = "DATE")
    private LocalDate releaseDate;

    @Column(name = "img")
    private String imgUrl;

    private String genre;

    // Default constructor
    public Book() {

    }

    // Constructor with fields
    public Book(String bookName, String author, LocalDate releaseDate, String imgUrl, String genre) {
        this.bookName = bookName;
        this.author = author;
        this.releaseDate = releaseDate;
        this.imgUrl = imgUrl;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
