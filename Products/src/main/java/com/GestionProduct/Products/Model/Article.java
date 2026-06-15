package com.GestionProduct.Products.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Content is required")
    private String content;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
}