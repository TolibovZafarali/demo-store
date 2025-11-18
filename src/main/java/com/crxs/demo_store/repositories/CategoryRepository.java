package com.crxs.demo_store.repositories;

import com.crxs.demo_store.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}
