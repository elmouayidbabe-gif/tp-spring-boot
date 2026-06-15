package com.GestionProduct.Products.Controller;

import com.GestionProduct.Products.Service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentPageController {

    private final CommentService commentService;

    public CommentPageController(CommentService commentService) {
        this.commentService = commentService;
    }

    // ADD COMMENT
    @PostMapping("/articles/{articleId}/comments")
    public String addComment(@PathVariable Long articleId,
                             @RequestParam String content) {

        if (content == null || content.trim().isEmpty()) {
            return "redirect:/articles/" + articleId;
        }

        commentService.addComment(articleId, content);

        return "redirect:/articles/" + articleId;
    }

    // DELETE COMMENT
    @GetMapping("/articles/{articleId}/comments/delete/{id}")
    public String deleteComment(@PathVariable Long articleId,
                                @PathVariable Long id) {

        commentService.delete(id);

        return "redirect:/articles/" + articleId;
    }
}