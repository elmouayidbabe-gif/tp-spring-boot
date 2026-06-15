package com.GestionProduct.Products.Controller;

import com.GestionProduct.Products.Model.Article;
import com.GestionProduct.Products.Service.ArticleService;
import com.GestionProduct.Products.Service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArticlePageController {

    private final ArticleService articleService;
    private final CommentService commentService;

    public ArticlePageController(ArticleService articleService,
                                 CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    // LIST
    @GetMapping("/articles")
    public String list(Model model) {
        model.addAttribute("articles", articleService.getAll());
        return "articles";
    }

    // NEW FORM
    @GetMapping("/articles/new")
    public String newArticle(Model model) {
        model.addAttribute("article", new Article());
        return "article-form";
    }

    // SAVE ARTICLE
    @PostMapping("/articles/save")
    public String save(@ModelAttribute Article article) {
        articleService.save(article);
        return "redirect:/articles";
    }

    // DETAILS + COMMENTS
    @GetMapping("/articles/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.getById(id));
        model.addAttribute("comments", commentService.getByArticleId(id));
        return "article";
    }

    // DELETE ARTICLE
    @GetMapping("/articles/delete/{id}")
    public String delete(@PathVariable Long id) {
        articleService.delete(id);
        return "redirect:/articles";
    }
}