package com.GestionProduct.Products.Repository;

import com.GestionProduct.Products.Model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}