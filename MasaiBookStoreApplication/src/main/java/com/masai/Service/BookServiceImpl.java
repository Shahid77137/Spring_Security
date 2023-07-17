package com.masai.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.BookException;
import com.masai.Exception.UserException;
import com.masai.Model.Book;
import com.masai.Model.User;
import com.masai.Repository.BookRepository;
import com.masai.Repository.UserRepository;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	public BookRepository bookRepository;
	@Autowired
	public UserRepository userRepository;
	
	@Override
	public Book addBook(Book book) throws BookException {
		// TODO Auto-generated method stub
		
		if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        if (book.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (book.getAuthor().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
        if (book.getISBN().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be empty");
        }
        if (book.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (book.getPrice() == 0.0) {
        	throw new IllegalArgumentException("price cannot be 0");
        }
        
        
        return bookRepository.save(book);

	}

	@Override
	public Book getBookByIsbn(String isbn) throws BookException {
		// TODO Auto-generated method stub
		
		Optional<Book> book =  bookRepository.findByISBN(isbn);
		
		if(book.isPresent()) {
			
			Book b = book.get();
			return b;
			
		}else {
			throw new BookException("No Book found");
		}
		
	}

	@Override
	public List<Book> searchBooksByTitle(String title) throws BookException {
		// TODO Auto-generated method stub
		
		List<Book> book =  bookRepository.findByTitle(title);
		
          if(book.isEmpty()) {
			
			List<Book> b = book;
			return b;
			
		}else {
			throw new BookException("No Book found");
		}
		
	}

	@Override
	public List<Book> searchBooksByAuthor(String author) throws BookException {
		// TODO Auto-generated method stub
		
		List<Book> book =  bookRepository.findByAuthor(author);
		
        if(book.isEmpty()) {
			
			List<Book> b = book;
			return b;
			
		}else {
			throw new BookException("No Book found");
		}

	}

	@Override
	public List<Book> getAllBooks() throws BookException {
		// TODO Auto-generated method stub
		
		List<Book> book =  bookRepository.findAll();
		
		 if(book.isEmpty()) {
				
				List<Book> b = book;
				return b;
				
			}else {
				throw new BookException("No Book found");
			}

	}

	@Override
	public Book updateBook(Book updatedBook) throws BookException {
		// TODO Auto-generated method stub
		
		
	        return bookRepository.save(updatedBook);

	}

	@Override
	public void deleteBookByIsbn(String isbn) throws BookException {
		// TODO Auto-generated method stub
		
	}


	

}
