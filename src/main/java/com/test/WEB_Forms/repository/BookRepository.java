package com.test.WEB_Forms.repository;

import com.test.WEB_Forms.model.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "EXEC SelectBooks", nativeQuery = true)
    List<Book> findAllBooks();

    @Query(value = "EXEC SelectBookById :id", nativeQuery = true)
    Optional<Book> findById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "EXEC InsertBook :title, :author, :year, :content", nativeQuery = true)
    void insertBook(@Param("title") String title, @Param("author") String author, @Param("year") int year, @Param("content") String content);

    @Modifying
    @Transactional
    @Query(value = "EXEC UpdateBook :id, :title, :author, :year, :content", nativeQuery = true)
    void updateBook(@Param("id") Long id, @Param("title") String title, @Param("author") String author, @Param("year") int year, @Param("content") String content);

    @Modifying
    @Transactional
    @Query(value = "EXEC DeleteBook :id", nativeQuery = true)
    void deleteBook(@Param("id") Long id);
}
