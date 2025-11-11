package com.crxs.demo_store.repositories;

import com.crxs.demo_store.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
