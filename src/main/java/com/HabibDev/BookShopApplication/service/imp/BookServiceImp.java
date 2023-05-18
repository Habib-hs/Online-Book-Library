package com.HabibDev.BookShopApplication.service.imp;

import com.HabibDev.BookShopApplication.entity.BookEntity;
import com.HabibDev.BookShopApplication.exception.custom.BookNotFoundException;
import com.HabibDev.BookShopApplication.model.BookRequestModel;
import com.HabibDev.BookShopApplication.repository.BookRepository;
import com.HabibDev.BookShopApplication.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;


    //Book Creation Implementation
    @Override
    public ResponseEntity<Object> addBook(BookRequestModel requestModel) {
        if (requestModel == null || !isValidRequestModel(requestModel)) {
            throw new IllegalArgumentException("Invalid book request");
        }

        BookEntity bookEntity = BookEntity.builder()
                .title(requestModel.getTitle())
                .author(requestModel.getAuthor())
                .price(requestModel.getPrice())
                .pageCount(requestModel.getPageCount())
                .build();
        bookRepository.save(bookEntity);

        // If the save operation is successful, return a success message
        return new ResponseEntity<>("Book added successfully", HttpStatus.CREATED);
    }

//validate the book creation
private boolean isValidRequestModel(BookRequestModel requestModel) {
    // Check if name is not null and not empty
    if (requestModel.getTitle() == null || requestModel.getTitle().isEmpty()) {
        return false;
    }

    // Check if author is not null and not empty
    if (requestModel.getAuthor() == null || requestModel.getAuthor().isEmpty()) {
        return false;
    }

    // Check if price is not null and greater than zero
    if (requestModel.getPrice() == null || requestModel.getPrice() <= 0) {
        return false;
    }

    // Check if pageCount is not null and greater than zero
    if (requestModel.getPageCount() == null || requestModel.getPageCount() <= 0) {
        return false;
    }

    // All validations passed
    return true;
}



    // For getting all the books Implementation
    @Override
    public ResponseEntity<Object> getAllBooks() {
        List<BookEntity> books = bookRepository.findAll();

        if (books.isEmpty()) {
            throw new BookNotFoundException("No books found");
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    //For Delete a particular Book Implementation

    @Override
    public ResponseEntity<Object> deleteBook(Integer bookId) {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
            return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
    }

    // for getting a book details - Implementation
    @Override
    public ResponseEntity<Object> getBook(Integer bookId) {
        Optional<BookEntity> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isPresent()) {
            BookEntity book = bookOptional.get();
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Book not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


    //For Searching all the books by author Name - Implementation
    @Override
    public ResponseEntity<Object> getBooksByAuthor(String authorName) {
        List<BookEntity> books = bookRepository.findByAuthor(authorName);
        if (books.isEmpty()) {
            return new ResponseEntity<>("No books found for author: " + authorName, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
    }


    //for getting a book using author and book title Implementation
    @Override
    public ResponseEntity<Object> getBooksByAuthorAndTitle(String authorName, String title) {
        BookEntity bookEntity = bookRepository.findByAuthorAndTitle(authorName, title);
        if (bookEntity == null) {
            return new ResponseEntity<>("No books found for author: " + authorName, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(bookEntity, HttpStatus.OK);
        }
    }


    //For Updating a Book - Implementation
    @Override
    public ResponseEntity<Object> update(Integer bookId, BookRequestModel requestModel) {
        Optional<BookEntity> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            BookEntity bookEntity = optionalBook.get();

            // Update the book entity with the new values from the request model
            bookEntity.setTitle(requestModel.getTitle());
            bookEntity.setAuthor(requestModel.getAuthor());
            bookEntity.setPrice(requestModel.getPrice());
            bookEntity.setPageCount(requestModel.getPageCount());

            // Save the updated book entity
            BookEntity updatedBook = bookRepository.save(bookEntity);

            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
    }

}
