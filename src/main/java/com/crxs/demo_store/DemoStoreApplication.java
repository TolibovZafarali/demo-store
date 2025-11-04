package com.crxs.demo_store;

import com.crxs.demo_store.entities.Address;
import com.crxs.demo_store.entities.Profile;
import com.crxs.demo_store.entities.Tag;
import com.crxs.demo_store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoStoreApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DemoStoreApplication.class, args);

		var user = User.builder()
				.name("Smith")
				.email("smith@gmail.com")
				.password("password")
				.build();

		var address = Address.builder()
				.street("street")
				.city("city")
				.state("state")
				.zip("zip")
				.build();

		user.addTag("tag1");

		user.addAddress(address);

		var profile = Profile.builder()
						.bio("Some text")
						.build();

		user.setProfile(profile);
		profile.setUser(user);

		System.out.println(user);
	}

}
