package com.crxs.demo_store.services;

import com.crxs.demo_store.entities.Category;
import com.crxs.demo_store.entities.Product;
import com.crxs.demo_store.repositories.CategoryRepository;
import com.crxs.demo_store.repositories.ProductRepository;
import com.crxs.demo_store.repositories.UserRepository;
import com.crxs.demo_store.repositories.specifications.ProductSpec;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;

import org.springframework.data.jpa.domain.Specification;
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

    @Transactional
    public void updateProductPrices() {
        productRepository.updatePriceByCategory(new BigDecimal("10.00"), (byte) 1);
    }

    @Transactional
    public void fetchProducts() {
        var product = new Product();
        product.setName("iPhone");
        product.setDescription("Yes");

        var matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIncludeNullValues()
                .withIgnorePaths("id", "description")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(product, matcher);

        var products = productRepository.findAll(example);
        products.forEach(p -> {
            System.out.println(p.getName());
        });
    }

    public void fetchProductsByCriteria() {
        var products = productRepository.findProductsByCriteria(null, new BigDecimal("1"), new BigDecimal("10"), new Category((byte) 2));
        products.forEach(System.out::println);
    }

    public void fetchProductsBySpecifications(String name, BigDecimal minPrice, BigDecimal maxPrice, Category category) {
        Specification<Product> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(ProductSpec.hasName(name));
        }
        if (minPrice != null) {
            spec = spec.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }
        if (category != null) {
            spec = spec.and(ProductSpec.hasCategory(category));
        }

        productRepository.findAll(spec).forEach(System.out::println);
    }

    public void fetchSortedProducts() {
        var sort = Sort.by("name").and(
                Sort.by("price").descending()
        );

        productRepository.findAll(sort).forEach(System.out::println);
    }

    public void fetchPaginatedProducts(int pageNumber, int size) {
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        Page<Product> page = productRepository.findAll(pageRequest);

        var products = page.getContent();
        products.forEach(System.out::println);

        var totalPages = page.getTotalPages();
        var totalElements = page.getTotalElements();
        System.out.println("Total Pages: " + totalPages);
        System.out.println("Total Elements: " + totalElements);
    }
}
