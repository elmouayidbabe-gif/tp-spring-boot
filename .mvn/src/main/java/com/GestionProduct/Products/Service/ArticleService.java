package com.GestionProduct.Products.Service;

import com.GestionProduct.Products.Model.Article;
import com.GestionProduct.Products.Repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // 📌 جلب كل المقالات
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    // 📌 جلب مقال حسب ID
    public Article getById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));
    }

    // 📌 حفظ / إنشاء مقال
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    // 📌 حذف مقال
    public void delete(Long id) {
        articleRepository.deleteById(id);
    }
}