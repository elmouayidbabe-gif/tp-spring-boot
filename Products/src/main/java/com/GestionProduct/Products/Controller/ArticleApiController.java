package com.GestionProduct.Products.Controller;

import com.GestionProduct.Products.Model.Article;
import com.GestionProduct.Products.Service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleApiController {

    private final ArticleService service;

    public ArticleApiController(ArticleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Article> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Article create(@RequestBody Article article) {
        return service.save(article);
    }
}