package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.repo.ProductRepo;
import com.example.demo.model.Product;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    public List<Product> addProducts(Product product) {
        productRepo.save(product);
        return productRepo.findAll();
    }

    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(null);
    }

    public Product addProducts(Product product, MultipartFile imagFile) throws IOException {
        product.setImageName(imagFile.getOriginalFilename());
        product.setImageType(imagFile.getContentType());
        product.setImageData(imagFile.getBytes());
        return productRepo.save(product);
    }
}
