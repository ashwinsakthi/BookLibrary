package com.library.book.controller;

import com.library.book.model.Book;
import com.library.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


/**
     * REST controller for {@link Book} connected requests
     */

    @RestController
    @RequestMapping("/api/v1/books/")
    public class BookRestControllerV1 {

        @Autowired
        private BookService bookService;

        @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Book> getBook(@PathVariable("id") Long bookId) {
            if (bookId == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Optional<Book> book = this.bookService.getById(bookId);

            if (book == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<Book>(book.get(), HttpStatus.OK);
        }

        @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Book> saveBook(@RequestBody @Valid Book book) {
            HttpHeaders headers = new HttpHeaders();

            if (book == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            this.bookService.save(book);
            return new ResponseEntity<>(book, headers, HttpStatus.CREATED);
        }

        @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Book> updateBook(@RequestBody @Valid Book book, UriComponentsBuilder builder) {
            HttpHeaders headers = new HttpHeaders();

            if (book == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            this.bookService.save(book);

            return new ResponseEntity<>(book, headers, HttpStatus.OK);
        }

        @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Book> deleteBook(@PathVariable("id") Long id) {
            Optional<Book> book = this.bookService.getById(id);

            if (book == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            this.bookService.delete(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<Book>> getAllBooks() {
            List<Book> books = this.bookService.getAll();

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(books, HttpStatus.OK);
        }


    }
