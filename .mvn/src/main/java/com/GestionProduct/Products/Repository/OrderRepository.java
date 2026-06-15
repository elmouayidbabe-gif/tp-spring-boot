package com.GestionProduct.Products.Repository;

import com.GestionProduct.Products.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
