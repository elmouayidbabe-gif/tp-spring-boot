package com.GestionProduct.Products.Service;

import com.GestionProduct.Products.Model.Article;
import com.GestionProduct.Products.Model.Comment;
import com.GestionProduct.Products.Repository.ArticleRepository;
import com.GestionProduct.Products.Repository.CommentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentService(CommentRepository commentRepository,
                          ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    public List<Comment> getByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    // ADD COMMENT
    public void addComment(Long articleId, String content) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        Comment c = new Comment();
        c.setArticle(article);
        c.setText(content);
        c.setUsername(username);

        commentRepository.save(c);
    }

    // DELETE COMMENT (USER OR ADMIN)
    public void delete(Long commentId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        boolean isOwner = comment.getUsername().equals(username);

        if (isAdmin || isOwner) {
            commentRepository.delete(comment);
        } else {
            throw new RuntimeException("Not allowed");
        }
    }
}