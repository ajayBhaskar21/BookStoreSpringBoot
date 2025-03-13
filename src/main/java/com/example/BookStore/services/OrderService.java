package com.example.BookStore.services;

import com.example.BookStore.models.Order;
import com.example.BookStore.models.User;
import com.example.BookStore.models.Book;
import com.example.BookStore.repositories.OrderRepository;
import com.example.BookStore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get all orders for a specific user
    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    // Get order by ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Place an order
    public Order placeOrder(Order order, Long userId, List<Long> bookIds) {
        order.setOrderStatus("Pending"); // Default status

        // Fetch books by IDs and set them to order
        List<Book> books = bookRepository.findAllById(bookIds);
        order.setBooks(books);

        return orderRepository.save(order);
    }

    // Update order status
    public Optional<Order> updateOrderStatus(Long id, String status) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setOrderStatus(status);
            orderRepository.save(order);
            return Optional.of(order);
        }
        return Optional.empty();
    }
}
