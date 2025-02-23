package com.venkat;

import com.venkat.productentity.Product;
import com.venkat.productrepository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultipleDatabaseDemoApplication implements CommandLineRunner {

	@Autowired
	private ProductDAO productDAO;

	public static void main(String[] args) {
		SpringApplication.run(MultipleDatabaseDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product product = new Product(2,"laptop",25000.00);
		productDAO.save(product);


	}
}
