package com.flipkart.flipkartapi.controller;

import java.util.List;

import com.flipkart.flipkartapi.Services.OrderService;
import com.flipkart.flipkartapi.dto.OrdersData;
import com.flipkart.flipkartapi.dto.ProductData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/order")
@CrossOrigin(origins = "*")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public List<OrdersData> findAll() {
		return orderService.findAll();
	}

	@GetMapping("{id}")
	public OrdersData findById(@PathVariable Long id) {
		return orderService.findById(id);

	}

	@PostMapping
	public OrdersData create(@RequestBody OrdersData ordersData) {
		return orderService.create(ordersData);

	}

	@DeleteMapping("{id}")
	public boolean delete(@PathVariable Long id) {
		return orderService.delete(id);
	}

	@PutMapping("{id}")
	public boolean updateOrder(@RequestBody OrdersData ordersData, @PathVariable("id") Long orderId) {
		return orderService.update(ordersData, orderId);

	}

}
