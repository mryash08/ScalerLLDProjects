package com.example.ecom.models;

import jakarta.persistence.Entity;
import lombok.Data;


@Entity(name = "product")
@Data
public class Product extends BaseModel{
    private String name;
    private String description;
    private double price;
}
