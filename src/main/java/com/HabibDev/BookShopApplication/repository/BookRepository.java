package com.HabibDev.BookShopApplication.repository;

import com.HabibDev.BookShopApplication.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity,Integer> {
    List<BookEntity> findByAuthor(String authorName);

    BookEntity findByAuthorAndTitle(String authorName, String title);

    boolean existsByTitleAndAuthor(String title, String author);
}