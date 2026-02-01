package com.example.BookStore.services;

import com.example.BookStore.models.Order;
import com.example.BookStore.models.User;
import com.example.BookStore.models.Book;
import com.example.BookStore.repositories.OrderRepository;
import com.example.BookStore.repositories.BookRepository;
import com.example.BookStore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
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
        order.setOrderStatus("PENDING"); // Default status

        // Fetch books by IDs and set them to order
        List<Book> books = bookRepository.findAllById(bookIds);
        order.setBooks(books);

        Optional<User> user =  userRepository.findById(userId);
        order.setUser(user.get());


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

    public boolean deleteOrder(Long id) {
        Optional<Order> existingOrder= orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setDeleted(true);
            orderRepository.save(order);
            return true;
        }
        return false;
    }
}
