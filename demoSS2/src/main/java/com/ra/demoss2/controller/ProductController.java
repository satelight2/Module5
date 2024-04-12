package com.ra.demoss2.controller;

import com.ra.demoss2.entity.ProductEntity;
import com.ra.demoss2.repository.ProductRepository;
import com.ra.demoss2.service.impl.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<ProductEntity> getProducts(@RequestParam(required = false) String keyword, @RequestParam(required = false) Integer batch) {
        return productService.findByKeyword(keyword, batch);
    }
}
