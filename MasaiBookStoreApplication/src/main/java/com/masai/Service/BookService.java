package com.masai.Service;

import java.util.List;

import com.masai.Exception.BookException;
import com.masai.Model.Book;

public interface BookService {
 
	public Book addBook(Book book) throws BookException;
	
//	- Retrieve a specific book by ISBN: Users should be able to retrieve detailed information about a book using its unique ISBN.
	
	public Book getBookByIsbn(String isbn) throws BookException;
	
//	- Retrieve book/books: Users should be able to search for books based on title, and author as well.

	public List<Book> searchBooksByTitle(String title) throws BookException;
	public List<Book> searchBooksByAuthor(String author) throws BookException;
	
//	- Retrieve all books: Users should be able to retrieve a list of all available books in the bookstore.
	
	public List<Book> getAllBooks() throws BookException;
	
//	- Update an existing book: Users with the "admin" role should be able to update the details of a book, such as its title, author, or price.
	
	public Book updateBook(Book updatedBook) throws BookException;
	
//	- Delete a book by ISBN: Users with the "admin" role should be able to remove a book from the inventory using its ISBN.
	public void deleteBookByIsbn(String isbn) throws BookException;
	
}
