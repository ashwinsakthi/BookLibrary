package com.library.book.service.impl;

import com.library.book.model.Book;
import com.library.book.repository.BookRepository;
import com.library.book.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;


    public Optional<Book> getById(Long id) {

        return bookRepository.findById(id);
    }

    @Override
    public void save(Book book) {

        bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {

        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getAll() {

        return bookRepository.findAll();
    }


}
