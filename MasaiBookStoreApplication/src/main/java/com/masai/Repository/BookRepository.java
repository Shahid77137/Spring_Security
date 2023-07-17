package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.masai.Model.Book;
//import com.masai.Service.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{

  Optional<Book> findByISBN(String isbn);
  List<Book> findByTitle(String title);
  List<Book> findByAuthor(String author);

}
