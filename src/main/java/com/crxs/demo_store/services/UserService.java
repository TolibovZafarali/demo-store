package com.crxs.demo_store.services;

import com.crxs.demo_store.entities.*;
import com.crxs.demo_store.repositories.*;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;

    @Transactional
    public void showEntityStates() {
        var user = User.builder()
                .name("Ali")
                .email("email@gmail.com")
                .password("password123")
                .build();

        if (entityManager.contains(user))
            System.out.println("Persistent");
        else
            System.out.println("Transient / Detached");

        userRepository.save(user);

        if (entityManager.contains(user))
            System.out.println("Persistent");
        else
            System.out.println("Transient / Detached");
    }

    @Transactional
    public void showRelatedEntities() {
        User user = userRepository.findById(2L).orElseThrow();

        var newProfile = Profile.builder()
                .user(user)
                .bio("Yes")
                .build();

        profileRepository.save(newProfile);

        var profile = profileRepository.findById(2L).orElseThrow();
        System.out.println(profile.getBio());
        System.out.println(profile.getUser().getEmail());
    }

    @Transactional
    public void fetchAddress() {
        var address = addressRepository.findById(1L).orElseThrow();

        System.out.println(address.getUser().getEmail());
    }

    public void persistRelated() {
        var user = User.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password123")
                .build();

        var address = Address.builder()
                .street("123 Main Street")
                .city("City")
                .state("ST")
                .zip("12345")
                .build();

        user.addAddress(address);

        userRepository.save(user);
    }

    @Transactional
    public void deleteRelated() {
        var user = userRepository.findById(1L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void fetchUsers() {
        var users = userRepository.findAllWithAddresses();

        users.forEach(user -> {
            System.out.println(user);
            user.getAddresses().forEach(System.out::println);
        });
    }

    @Transactional
    public void fetchProfiles(Integer loyaltyPoints) {
        var users = userRepository.findUsers(loyaltyPoints);

        users.forEach(user -> {
            System.out.println(user.getId());
            System.out.println(user.getEmail());
        });
    }

}
