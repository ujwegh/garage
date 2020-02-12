package ru.ilnik.garage.controller;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.ilnik.garage.model.Author;
import ru.ilnik.garage.model.Book;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class BookQueries implements GraphQLQueryResolver {

    public List<Book> allBooks() {
        log.info("Get all books");
        return books;
    }

    public Book bookById(Long id) {
        log.info("Get book by id {}", id);
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    private static List<Author> authors = Arrays.asList(
            new Author(0L, "fl0", "dl0"),
            new Author(1L, "fl1", "dl1"),
            new Author(2L, "fl2", "dl2"),
            new Author(3L, "fl3", "dl3")
    );


    private static List<Book> books = Arrays.asList(
            new Book(0L, "nm0", 234, authors.get(1)),
            new Book(1L, "nm1", 456, authors.get(2)),
            new Book(2L, "nm2", 45, authors.get(1)),
            new Book(3L, "nm3", 234, authors.get(3)),
            new Book(4L, "nm4", 5456, authors.get(3)),
            new Book(5L, "nm5", 2234, authors.get(0)),
            new Book(6L, "nm6", 23, authors.get(0)),
            new Book(7L, "nm7", 12, authors.get(2))
    );
}
