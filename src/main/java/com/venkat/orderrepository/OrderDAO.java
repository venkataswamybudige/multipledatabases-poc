package com.venkat.orderrepository;

import com.venkat.orderentity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderDAO extends CrudRepository<Order,Integer> {
}
