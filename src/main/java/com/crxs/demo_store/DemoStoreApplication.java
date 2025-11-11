package com.crxs.demo_store;

import com.crxs.demo_store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoStoreApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoStoreApplication.class, args);
		var service = context.getBean(UserService.class);

//		service.showEntityStates();
//		service.showRelatedEntities();
		service.fetchAddress();
	}

}
