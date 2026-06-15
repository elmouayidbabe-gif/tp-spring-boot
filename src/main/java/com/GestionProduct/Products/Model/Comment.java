package com.GestionProduct.Products.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;   // صاحب التعليق
    private String text;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
}