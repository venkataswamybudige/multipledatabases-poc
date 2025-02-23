package com.venkat.productrepository;

import com.venkat.productentity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product,Integer> {
}
