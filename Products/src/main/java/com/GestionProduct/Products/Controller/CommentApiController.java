package com.GestionProduct.Products.Controller;

import com.GestionProduct.Products.Service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentApiController {

    private final CommentService commentService;

    public CommentApiController(CommentService commentService) {
        this.commentService = commentService;
    }

    // ADD COMMENT (FIXED)
    @PostMapping("/article/{articleId}")
    public String createComment(@PathVariable Long articleId,
                                @RequestParam String content) {

        if (content == null || content.trim().isEmpty()) {
            return "Content is empty";
        }

        commentService.addComment(articleId, content);

        return "Comment added successfully";
    }

    // DELETE COMMENT (FIXED)
    @DeleteMapping("/{commentId}/article/{articleId}")
    public String deleteComment(@PathVariable Long commentId,
                                @PathVariable Long articleId) {

        commentService.delete(commentId);

        return "Comment deleted successfully";
    }
}