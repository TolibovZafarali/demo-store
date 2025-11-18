package com.crxs.demo_store.services;

import com.crxs.demo_store.entities.Category;
import com.crxs.demo_store.entities.Product;
import com.crxs.demo_store.repositories.CategoryRepository;
import com.crxs.demo_store.repositories.ProductRepository;
import com.crxs.demo_store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createProduct() {
        var newCategory = Category.builder()
                .name("Tech")
                .build();

        var newProduct = Product.builder()
                .name("Mac")
                .price(new BigDecimal("999.99"))
                .build();

        newCategory.addProduct(newProduct);

        productRepository.save(newProduct);
    }

    @Transactional
    public void fetchCatAndAddPro() {
        var newProduct = Product.builder()
                .name("iPhone")
                .price(new BigDecimal("1199.99"))
                .build();

        var category = categoryRepository.findById((byte) 1).orElseThrow();
        category.addProduct(newProduct);

        productRepository.save(newProduct);
    }

    @Transactional
    public void fetchUserAndAddPros() {
        var allProducts = productRepository.findAll();
        var user = userRepository.findById(1L).orElseThrow();

        for (Product product : allProducts) {
            user.addProduct(product);
        }
    }

    @Transactional
    public void deleteProduct() {
        var product = productRepository.findById(1L).orElseThrow();
        var category = product.getCategory();

        if (category != null) {
            category.removeProduct(product);
        }

        productRepository.delete(product);
    }

    @Transactional
    public void check() {
        var category = categoryRepository.findById((byte) 1).orElseThrow();

        var products = category.getProducts();

        for (Product product : products) {
            System.out.println(product.getName());
        }
    }
}
