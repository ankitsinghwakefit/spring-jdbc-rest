package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
@CrossOrigin // for cross-origin requests it will solve CORS issues
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String greet() {
        return "Hello from the ProductController!";
    }

    // with Response Entity we can send response with status and other information
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    // without image
    // @PostMapping("/products")
    // public List<Product> addProduct(@RequestBody Product product) {
    // return productService.addProducts(product);
    // }

    @GetMapping("/products/{prodId}")
    public ResponseEntity<Product> getProductById(@PathVariable int prodId) {
        Product targeProduct = productService.getProductById(prodId);
        if (targeProduct != null) {
            return new ResponseEntity<>(targeProduct, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // with image
    // requestBody accepts json as whole object
    // RequestPart accepts json in parts so we can target individual keys
    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imagFile) {
        try {
            Product products = productService.addProducts(product, imagFile);
            return new ResponseEntity<>(products, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
