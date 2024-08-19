package com.example.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotEmpty(message = "Không được để trống")
    private String name;

    @NotNull
    @DecimalMin(value = "0",inclusive = false,message = "Price phải lớn hơn 0")
    private double price;

    private String image;

    @NotNull
    @NotEmpty(message = "Không được để trống")
    private String detailDesc;

    @NotNull
    @NotEmpty(message = "Không được để trống")
    private String shortDesc;

    @Min(value = 1, message = "Không được nhỏ hơn 1")
    private long quantity;

    private long sold;
    private String fatory;
    private String target;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getSold() {
        return sold;
    }

    public void setSold(long sold) {
        this.sold = sold;
    }

    public String getFatory() {
        return fatory;
    }

    public void setFatory(String fatory) {
        this.fatory = fatory;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}