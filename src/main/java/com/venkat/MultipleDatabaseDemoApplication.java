package com.venkat;

import com.venkat.orderentity.Order;
import com.venkat.orderrepository.OrderDAO;
import com.venkat.productentity.Product;
import com.venkat.productrepository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class MultipleDatabaseDemoApplication implements CommandLineRunner {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private OrderDAO orderDAO;

	public static void main(String[] args) {
		SpringApplication.run(MultipleDatabaseDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product product = new Product(3,"mobile",25000.00);
		productDAO.save(product);

		Order order = new Order(3, "thirdORDER", LocalDate.now());
		orderDAO.save(order);


	}
}
