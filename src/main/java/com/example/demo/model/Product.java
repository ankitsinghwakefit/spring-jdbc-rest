package com.example.demo.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Product entity with JPA annotations
@Entity

// related to lombok entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String brand;
    private String category;
    private BigDecimal price;
    // to format the date of the product before saving to database
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy")
    // this.getDate(Date releasDate)
    private Date releasDate;
    private boolean available;
    private int quantity;

    // Image part
    private String imageName;
    private String imageType;
    @Lob // large object annotation
    private byte[] imageData;

    // public String getDate(Date releasDate){
    // return releasDate.toString().substring(0, 10);
    // }
}
