package com.HabibDev.BookShopApplication.controller;


import com.HabibDev.BookShopApplication.entity.BookEntity;
import com.HabibDev.BookShopApplication.model.BookRequestModel;
import com.HabibDev.BookShopApplication.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    //create a book
    @PostMapping("/create")
    public ResponseEntity<Object> addBook(@RequestBody BookRequestModel requestModel) {
        return bookService.addBook(requestModel);
    }

    //get a book
    @GetMapping("/id/{bookId}")
    public ResponseEntity<Object>getBookById(@PathVariable Integer bookId){
        return bookService.getBook(bookId);
    }

    //get all books for an author
    @GetMapping("/author/{authorName}")
    public ResponseEntity<Object> getBooksByAuthor(@PathVariable String authorName) {
     return bookService.getBooksByAuthor(authorName);
    }


    //get a book details using author and book name
    @GetMapping("{authorName}/{bookName}")
    public ResponseEntity<Object> getBooksByAuthorAndTitle(@PathVariable String authorName, @PathVariable String bookName){
        return bookService.getBooksByAuthorAndTitle(authorName,bookName) ;
    }

    //get all book info
    @GetMapping("/all")
    public ResponseEntity<Object>getAllBooks(){
       return bookService.getAllBooks();
    }

    //delete a book
    @DeleteMapping ("/delete/{bookId}")
    public ResponseEntity<Object>deleteBookById(@PathVariable Integer bookId){
        System.out.println("it is come inside delete method");
        return bookService.deleteBook(bookId);
    }

    //update a book
    @PutMapping("/update/{bookId}")
    public ResponseEntity<Object>updateBookById(@PathVariable Integer bookId, @RequestBody BookRequestModel book){
        return bookService.update(bookId,book);
    }


}
