package com.crxs.demo_store.repositories;

import com.crxs.demo_store.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
