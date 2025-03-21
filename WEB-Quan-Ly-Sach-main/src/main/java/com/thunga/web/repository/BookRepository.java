package com.thunga.web.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thunga.web.entity.Author;
import com.thunga.web.entity.Book;
import com.thunga.web.entity.Category;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	@Query(value = "SELECT * FROM book ORDER BY number_sold DESC LIMIT 1", nativeQuery = true)
	Book findBestSoldBook();
	
	List<Book> findByTitle(String title);
	
	@Query(value = "SELECT * FROM book WHERE LOWER(title) LIKE LOWER(?1)", nativeQuery = true)
	List<Book> findBySimilarTitle(String title, Pageable pageable);
	
	List<Book> findByAuthor(Author author);
	
	@Query(value = "SELECT b.* FROM book b LEFT JOIN author a ON b.author_id = a.id WHERE LOWER(a.name) LIKE LOWER(?1)", nativeQuery = true)
	List<Book> findBySimilarAuthor(String authorName, Pageable pageable);
	
	List<Book> findByCategory(Category category, Pageable pageable);
	
}

