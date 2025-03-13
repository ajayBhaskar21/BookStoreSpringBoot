package com.example.BookStore.repositories;

import com.example.BookStore.models.Order;
import com.example.BookStore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // orders by a specific user
    List<Order> findByUser(User user);
    // orders by status (pending or completed)
    List<Order> findByOrderStatus(String orderStatus);


}
