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
@Table(name = "Book")
public class BookEntity {
    @Id
    @GeneratedValue
    private Integer bookId;
    private String title;
    private String author;
    private Integer price;
    private Integer pageCount;

    // Add more properties as needed


}

