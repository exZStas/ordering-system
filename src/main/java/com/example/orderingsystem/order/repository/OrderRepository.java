package com.example.orderingsystem.order.repository;

import com.example.orderingsystem.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
