package org.javatraining.thymeleafonetomanycrud;

import org.javatraining.thymeleafonetomanycrud.repository.ProductCategoryRepository;
import org.javatraining.thymeleafonetomanycrud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThymeleafonetomanyApplication implements CommandLineRunner {

	@Autowired
	private ProductCategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafonetomanyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
