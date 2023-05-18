
package com.HabibDev.BookShopApplication.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BookRequestModel {

    private Integer bookId;
    private String title;
    private String author;
    private Integer price;
    private Integer pageCount;

    // Add more properties as needed


}
