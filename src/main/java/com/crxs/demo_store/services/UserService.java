package com.crxs.demo_store.services;

import com.crxs.demo_store.entities.Profile;
import com.crxs.demo_store.entities.User;
import com.crxs.demo_store.repositories.AddressRepository;
import com.crxs.demo_store.repositories.ProfileRepository;
import com.crxs.demo_store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
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
}
