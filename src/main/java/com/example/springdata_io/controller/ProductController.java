package com.example.springdata_io.controller;

import com.example.springdata_io.dao.entity.Product;
import com.example.springdata_io.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("product/all")
    public Iterable<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("product")
    public Optional<Product> getProductByID(@RequestParam Long index) {
        return productService.findProductByID(index);
    }

    @PostMapping("admin/product")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("admin/product")
    public Product updateProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PatchMapping("admin/product")
    public void patchProduct(@RequestParam Long index, @RequestBody Product product) {
        Product product1 = productService.findProductByID(index)
                .orElseThrow(NoSuchElementException::new);
        boolean ifUpdate = false;

        if (StringUtils.hasLength(product.getName())) {
            product1.setName(product.getName());
            ifUpdate = true;
        }
        if (StringUtils.hasLength(String.valueOf(product.getPrice()))) {
            product1.setPrice(product.getPrice());
            ifUpdate = true;
        }
        if (ifUpdate) {
            productService.addProduct(product1);
        }
    }
}
