package com.HabibDev.BookShopApplication.service;

import com.HabibDev.BookShopApplication.model.BookRequestModel;
import org.springframework.http.ResponseEntity;

public interface BookService {

    ResponseEntity<Object> addBook(BookRequestModel requestModel);
    ResponseEntity<Object> getAllBooks();

    ResponseEntity<Object> getBooksByAuthor(String authorName);
    ResponseEntity<Object> getBooksByAuthorAndTitle(String authorName, String title);
    ResponseEntity<Object> deleteBook(Integer bookId);
    ResponseEntity<Object> getBook(Integer bookId);
    ResponseEntity<Object> update(Integer bookId,BookRequestModel requestModel);


}
