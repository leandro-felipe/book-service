package br.com.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bookservice.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
