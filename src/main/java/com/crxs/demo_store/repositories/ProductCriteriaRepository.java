package com.crxs.demo_store.repositories;

import com.crxs.demo_store.entities.Category;
import com.crxs.demo_store.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductCriteriaRepository {
    List<Product> findProductsByCriteria(String name, BigDecimal min, BigDecimal max, Category category);
}
