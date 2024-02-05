package com.ddungja.petmily.es;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TypeAlias("product")
@Document(indexName = "products")
public class Product {
    private int id;
    private String name;
    private String description;
    private int quantity;
}
