package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.BookException;
import com.masai.Model.Book;
import com.masai.Repository.BookRepository;
import com.masai.Repository.UserRepository;
import com.masai.Service.BookService;
import com.masai.Service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
@RestController
public class BookController {

	@Autowired
    private BookService bookService;
	@Autowired
	private BookRepository bookrepo;
	
	
	@PostMapping("/createBook")
    public ResponseEntity<Book> createBooking(@Valid @RequestBody Book book) {
    	
        Book BookRegistered = bookrepo.save(book);
        
        return new ResponseEntity<>(BookRegistered,HttpStatus.CREATED);
    }
	
    @GetMapping("/book/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) throws BookException{
       
            Book book = bookService.getBookByIsbn(isbn);
            
            if(book == null) {
            	throw new BookException("Book not found");
            }
            
            return new ResponseEntity<>(book,HttpStatus.OK);
       
    }
//    public List<Book> searchBooksByTitle(String title) throws BookException;
//	public List<Book> searchBooksByAuthor(String author) throws BookException;
    
    
    @GetMapping("/book/{title}")
    public ResponseEntity<List<Book>> searchBooksByTitle(@PathVariable String title) throws BookException{
       
            List<Book> book = bookService.searchBooksByTitle(title);
            
            if(book.isEmpty()) {
            	throw new BookException("Books not found");
            }
            
            return new ResponseEntity<>(book,HttpStatus.OK);
       
    }
    
    @GetMapping("/book/{author}")
    public ResponseEntity<List<Book>> searchBooksByAuthor(@PathVariable String author) throws BookException{
    	
    	List<Book> book = bookService.searchBooksByTitle(author);
    	
    	if(book.isEmpty()) {
    		throw new BookException("Books not found");
    	}
    	
    	return new ResponseEntity<>(book,HttpStatus.OK);
    	
    }
    @GetMapping("/Allbooks")
    public ResponseEntity<List<Book>> getAllBooks() throws BookException{
    	
    	List<Book> book = bookService.getAllBooks();
    	
    	if(book.isEmpty()) {
    		throw new BookException("Books not found");
    	}
    	
    	return new ResponseEntity<>(book,HttpStatus.OK);
    	
    }
    

	
	
}
