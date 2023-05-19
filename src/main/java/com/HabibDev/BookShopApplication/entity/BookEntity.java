package com.HabibDev.BookShopApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Books")
public class BookEntity {
    @Id
    @GeneratedValue
    private Integer bookId;
    private String title;
    private String author;
    private String details;
    private Integer price;
    private Integer pageCount;
}

