package com.crxs.demo_store.repositories;

import com.crxs.demo_store.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
