package com.library.book.service;
import com.library.book.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> getById(Long id);

    void save(Book customer);

    void delete(Long id);

    List<Book> getAll();


}
