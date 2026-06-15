package com.GestionProduct.Products.Service;

import com.GestionProduct.Products.Model.Order;
import com.GestionProduct.Products.Model.Product;
import com.GestionProduct.Products.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    public boolean buyProduct(Long productId, Integer quantity) {

        Product product = productService.getProductById(productId);

        // stock check
        if (product.getStock() < quantity) {
            return false;
        }

        // decrease stock
        product.setStock(product.getStock() - quantity);
        productService.saveProduct(product);

        // create order
        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);

        // save order
        orderRepository.save(order);

        return true;
    }
}