package com.GestionProduct.Products.Repository;

import com.GestionProduct.Products.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByArticleId(Long articleId);
}