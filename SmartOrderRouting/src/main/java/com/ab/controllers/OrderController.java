package com.ab.controllers;

import java.util.List;

import com.ab.entities.Order;
import com.ab.entities.OrderHistory;
import com.ab.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
   
	@Autowired
	private OrderService orderService;

    @GetMapping("/order/{orderId}")  //end point
	public Order getOrder(@PathVariable int orderId){
		return orderService.getOrder(orderId);
    }

	@GetMapping("/orders")
	public List<Order>getAllOrders(){
		return orderService.getAllOrders();

	}
	@GetMapping("/buyOrders/{orderId}")
	public List<Order> getAllBuyOrders(@PathVariable int orderId){
		return orderService.getAllBuyOrders();
	

	}
	@GetMapping("/sellOrders/{orderId}")
	public List<Order> getAllSellOrders(@PathVariable int orderId){
	 return orderService.getAllSellOrders();

	}
	@GetMapping("/orderStatus")
	public List<String> getAllOrderStatus(){
	 return orderService.getAllOrderStatus();
     
	}
	@PostMapping("/orderHistory")
	public OrderHistory addOrderHistory(@RequestBody OrderHistory orderHistory) {
		return orderService.addOrderHistory(orderHistory.getOrder().getOrderID(),orderHistory.getOrderID2(), orderHistory.getShareQuantity(), orderHistory.getValue());

	}
	@PutMapping("/cancelOrder/{orderID}")
	public Order cancelOrder(@PathVariable int orderID) {
		return orderService.cancelOrder(orderID);
	}

	@PutMapping("/updateOrder")
	public Order updateOrder(@RequestBody Order order) {
		return orderService.updateOrder(order.getOrderID(), order.getPriceLimit(), order.getShareQuantity());

	}
}