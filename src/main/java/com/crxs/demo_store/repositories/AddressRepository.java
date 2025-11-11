package com.crxs.demo_store.repositories;

import com.crxs.demo_store.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
