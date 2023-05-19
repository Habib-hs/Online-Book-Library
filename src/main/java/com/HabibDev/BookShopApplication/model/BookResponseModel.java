
package com.HabibDev.BookShopApplication.model;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseModel {
    private Integer bookId;
    private String title;
    private String author;
    private String details;
    private Integer price;
    private Integer pageCount;

    // Add more properties as needed


}

