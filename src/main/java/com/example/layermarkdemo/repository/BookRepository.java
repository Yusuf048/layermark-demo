package com.example.layermarkdemo.repository;

import com.example.layermarkdemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<List<Book>> findByGenre(String genre);
    Optional<List<Book>> findByAuthor(String author);
    Optional<List<Book>> findByReleaseDateAfter(LocalDate start);
    Optional<List<Book>> findByReleaseDateBefore(LocalDate end);
    Optional<List<Book>> findByReleaseDateBetween(LocalDate start, LocalDate end);
}
