package com.example.layermarkdemo;

import com.example.layermarkdemo.model.Book;
import com.example.layermarkdemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class LayermarkDemoApplication{

    public static void main(String[] args) {
        SpringApplication.run(LayermarkDemoApplication.class, args);
    }

    /*@Autowired
    private BookRepository bookRepository;*/

    /*@Override
    public void run(String... args) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        LocalDate dt = LocalDate.parse("2011-03-11");
        this.bookRepository.save(new Book("Physics of the Future", "Michio Kaku", LocalDate.parse("2011-03-11")));
        
    }*/
}

