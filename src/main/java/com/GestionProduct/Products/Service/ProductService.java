package com.GestionProduct.Products.Service;

import com.GestionProduct.Products.Model.Product;
import com.GestionProduct.Products.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class ProductService {
    @Autowired
    private ProductRepository repository ;
    public List<Product> getAllProducts(){
        return repository.findAll();
    }
    public Product getProductById(Long id){
        return repository.findById(id).orElse(null);

    }
    public Product saveProduct(Product product){
        return repository.save(product);
    }
    public Product updateProduct(Long id , Product newProduct){
        Product existingProduct= repository.findById(id).orElse(null);
        if (existingProduct != null){
            existingProduct.setName(newProduct.getName());
            existingProduct.setPrice(newProduct.getPrice());
            existingProduct.setDescription(newProduct.getDescription());
            existingProduct.setStock(newProduct.getStock());
            return repository.save(existingProduct);
        }
        return null;
    }
    public void deleteProduct(Long id){
        repository.deleteById(id);
    }
}
