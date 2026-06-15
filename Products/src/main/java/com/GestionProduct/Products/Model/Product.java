package com.GestionProduct.Products.Model;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private double price;
    private int stock;
    private String description;

}
