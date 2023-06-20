package com.order.service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.order.service.dbo.Order;
import com.order.service.exception.NotFoundException;
import com.order.service.services.OrderService;

/** This class contains all the endpoints related to order service.
 */
@RestController
public class OrderController {

    /**
     * The OrderService object.
     */
    @Autowired
    private OrderService orderService;

    /**
     * Places an order.
     * @param order The order to be placed.
     * @return The placed order.
     */
    @PostMapping("/placeOrder")
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    /**
     * Cancels an order by its ID.
     * @param orderId The ID of the order to be canceled.
     * @return A message indicating the successful deletion of the order.
     * @throws NotFoundException If the order is not found.
     */
    @DeleteMapping("/{orderId}")
    public String cancelOrder(@PathVariable Long orderId) throws NotFoundException {
        orderService.cancelOrder(orderId);
        return "Order deleted successfully for " + orderId;
    }

    /**
     * Retrieves a list of orders by buyer ID.
     * @param buyerId The ID of the buyer.
     * @return A list of orders associated with the buyer.
     */
    @GetMapping("/buyer/{buyerId}")
    public List<Order> getOrdersByBuyerId(@PathVariable Long buyerId) {
        return orderService.getOrdersByBuyerId(buyerId);
    }

    /**
     * Retrieves an order by its ID.
     * @param orderId The ID of the order to retrieve.
     * @return The retrieved order.
     * @throws NotFoundException If the order is not found.
     */
    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) throws NotFoundException {
        Optional<Order> order = orderService.getOrderById(orderId);
        return order.get();
    }

}
