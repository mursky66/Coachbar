package com.coachbar.pms.controller;

import com.coachbar.pms.dto.ApiResponce;
import com.coachbar.pms.entity.Product;
import com.coachbar.pms.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponce<Product>> createProduct(@RequestBody Product product) {

        log.info("Inside Save Product {} : ", product);
        productService.saveProduct(product);
        ApiResponce<Product> responce = ApiResponce.success("Product Created Successfully !!!");
        return ResponseEntity.ok(responce);
    }

    @GetMapping("/product-details")
    public ResponseEntity<List<Product>> getAllProduct() {
        log.info("Calling get All Product From Product");

        List<Product> productList = productService.getAllProductDetails();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/product-by-id/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        log.info("Calling get Product by id {} : ", id);
        Product byId = productService.getProductById(id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }


    @PutMapping("/update-product/{id}")
    public ResponseEntity<ApiResponce<Product>> updateProduct(@PathVariable Long id, @RequestBody Product updateProduct) {

        log.info("Calling Update Product {} : ", id, updateProduct);

        productService.updateProduct(id, updateProduct);
        ApiResponce<Product> responce = ApiResponce.success("Product update Successfully !!!");
        return new ResponseEntity<ApiResponce<Product>>(responce, HttpStatus.OK);

    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<ApiResponce<Product>> deleteProduct(@RequestParam long id) {

        log.info("Calling deletet product by id {} : ", id);
        productService.deleteProduct(id);
        ApiResponce<Product> responce = ApiResponce.success("Delete Product Successfully !!!");
        return new ResponseEntity<ApiResponce<Product>>(responce, HttpStatus.OK);
    }
}
