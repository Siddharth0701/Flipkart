package com.flipkart.flipkartapi.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.flipkart.flipkartapi.dao.OrderRepository;
import com.flipkart.flipkartapi.dto.OrdersData;
import com.flipkart.flipkartapi.exception.IdNotFoundException;
import com.flipkart.flipkartapi.model.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {
	@Autowired
	private OrderRepository orderRepository;

	private Orders getOrderEntity(OrdersData ordersData) {
		Orders orders = new Orders();
		orders.setId(ordersData.getId());
		orders.setEmail(ordersData.getEmail());
		orders.setAddress(ordersData.getAddress());
		orders.setContact(ordersData.getContact());
		orders.setTotalPrice(ordersData.getTotalPrice());
		orders.setUsers(ordersData.getUsers());
		return orders;

	}

	private OrdersData getOrderData(Orders orders) {
		OrdersData ordersData = new OrdersData();
		ordersData.setId(orders.getId());
		ordersData.setEmail(orders.getEmail());
		ordersData.setAddress(orders.getAddress());
		ordersData.setContact(orders.getContact());
		ordersData.setTotalPrice(orders.getTotalPrice());
		ordersData.setUsers(orders.getUsers());
		return ordersData;

	}

	@Override
	public List<OrdersData> findAll() {
		List<OrdersData> orderDataList = new ArrayList<>();
		List<Orders> orders = orderRepository.findAll();
		orders.forEach(order -> {
			orderDataList.add(getOrderData(order));
		});

		return orderDataList;
	}

	@Override
	public OrdersData findById(Long id) throws IdNotFoundException {
		Optional<Orders> orderOptional = orderRepository.findById(id);
		if (orderOptional == null) {
			new IdNotFoundException("Order not found");
		}

		return getOrderData(orderOptional.get());
	}

	@Override
	public OrdersData create(OrdersData ordersData) {
		Orders order = getOrderEntity(ordersData);
		return getOrderData(orderRepository.save(order));
	}

	@Override
	public boolean delete(Long id) {
		Orders orders = orderRepository.findById(id).get();
		if (orders != null) {
			orderRepository.deleteById(id);
			return true;

		}
		return false;
	}

	@Override
	public boolean update(OrdersData ordersData, Long id) {
		Orders orders = orderRepository.findById(id).get();
		if (orders != null) {
			orders.setEmail(ordersData.getEmail());
			orders.setAddress(ordersData.getAddress());
			orders.setContact(ordersData.getContact());
			orders.setTotalPrice(ordersData.getTotalPrice());
			orders.setUsers(ordersData.getUsers());
			orderRepository.save(orders);
			return true;

		}
		return false;
	}

}
