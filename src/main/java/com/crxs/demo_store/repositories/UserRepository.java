package com.crxs.demo_store.repositories;

import com.crxs.demo_store.dtos.UserSummary;
import com.crxs.demo_store.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @EntityGraph(attributePaths = {"tags", "addresses"})
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = "addresses")
    @Query("select u from User u")
    List<User> findAllWithAddresses();

    @Query(value = """
            SELECT u.id AS id, u.email AS email
            FROM users u
            JOIN profiles p ON u.id = p.id
            WHERE p.loyalty_points > :loyaltyPoints
            ORDER BY u.email
            """, nativeQuery = true)
    List<UserSummary> findUsers(@Param("loyaltyPoints") Integer loyaltyPoints);
}
