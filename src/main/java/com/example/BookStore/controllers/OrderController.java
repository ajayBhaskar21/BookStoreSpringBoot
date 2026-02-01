package com.example.BookStore.controllers;

import com.example.BookStore.models.Order;
import com.example.BookStore.repositories.OrderRepository;
import com.example.BookStore.services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // GET all orders
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // GET an order by ID
    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    // POST a new order
    @PostMapping("/orders/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestBody Map<String, Object> orderRequest) {

        Long userId = Long.valueOf(orderRequest.get("userId").toString());

        // Object mapper
        ObjectMapper objectMapper = new ObjectMapper();
        Order orderObj = objectMapper.convertValue(
                orderRequest.get("order"),
                Order.class
        );

        // without object mapper
        // LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) orderRequest.get("order");
        // System.out.println(map.get("totalPrice"));

        List<Long> bookIds = (List<Long>) orderRequest.get("bookIds");
        Order savedOrder = orderService.placeOrder(orderObj, userId, bookIds);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // PUT update order status
    @PutMapping("/order/{id}/updateStatus")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id,
            @RequestBody Map<String, String> body) {

        String status = body.get("status");
        Optional<Order> updatedOrder = orderService.updateOrderStatus(id, status);
        return updatedOrder.map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE soft delete an order
    @DeleteMapping("/order/{id}/delete")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        boolean deleteStatus = orderService.deleteOrder(id);

        return deleteStatus ? new ResponseEntity<>("Order deleted successfully", HttpStatus.OK) :
                new ResponseEntity<>("Order Id not found", HttpStatus.NOT_FOUND);
    }


}
