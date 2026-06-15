package com.GestionProduct.Products.Controller;

import com.GestionProduct.Products.Model.Product;
import com.GestionProduct.Products.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.GestionProduct.Products.Service.OrderService;

@Controller
@RequestMapping("/products")
public class ProductPageController {

    @Autowired
    private ProductService service;

    @Autowired
    private OrderService orderService;

    // LIST PRODUCTS
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", service.getAllProducts());
        return "products";
    }

    // ADD FORM
    @GetMapping("/add-form")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "index";
    }

    // EDIT FORM
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = service.getProductById(id);
        model.addAttribute("product", product);
        return "index";
    }

    // SAVE
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product) {
        service.saveProduct(product);
        return "redirect:/products";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return "redirect:/products";
    }

    // DETAILS
    @GetMapping("/detail/{id}")
    public String detailProduct(@PathVariable Long id, Model model) {
        Product product = service.getProductById(id);
        model.addAttribute("product", product);
        return "detail-product";
    }

    // =========================
    // SHOW BUY FORM
    // =========================
    @GetMapping("/buy-form/{id}")
    public String showBuyForm(@PathVariable Long id, Model model) {

        Product product = service.getProductById(id);

        model.addAttribute("product", product);

        return "buy-form";
    }
    // =========================
    // CONFIRM PURCHASE
    // =========================
    @PostMapping("/buy/{id}")
    public String buyProduct(@PathVariable Long id,
                             @RequestParam Integer quantity) {

        boolean success = orderService.buyProduct(id, quantity);

        return "redirect:/products";
    }

}