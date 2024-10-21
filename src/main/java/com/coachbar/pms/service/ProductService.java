package com.coachbar.pms.service;

import com.coachbar.pms.entity.Product;
import com.coachbar.pms.exception.ResourceNotFoundException;
import com.coachbar.pms.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAllProductDetails() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found In Database"));
    }


    public Product updateProduct(Long id, Product updateProduct) {
        Product productById = getProductById(id);
        productById.setName(updateProduct.getName());
        productById.setDescription(updateProduct.getDescription());
        productById.setPrice(updateProduct.getPrice());
        productById.setQuantity(updateProduct.getQuantity());
        return productRepository.save(productById);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
