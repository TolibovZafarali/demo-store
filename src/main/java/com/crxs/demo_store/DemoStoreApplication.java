package com.crxs.demo_store;

import com.crxs.demo_store.services.ProductService;
import com.crxs.demo_store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoStoreApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoStoreApplication.class, args);
		var service = context.getBean(ProductService.class);

//		service.showEntityStates();
//		service.showRelatedEntities();
//		service.fetchAddress();
//		service.persistRelated();
//		service.deleteRelated();
//		service.createProduct();
//		service.fetchCatAndAddPro();
//		service.fetchUserAndAddPros();
//		service.deleteProduct();
		service.check();
	}

}
