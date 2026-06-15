package com.GestionProduct.Products.Repository;

import com.GestionProduct.Products.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long> {
}
