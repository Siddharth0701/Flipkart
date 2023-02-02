package com.flipkart.flipkartapi.controller;

import java.util.List;

import com.flipkart.flipkartapi.Services.CartService;
import com.flipkart.flipkartapi.dto.CartData;

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
@RequestMapping("api/cart")
@CrossOrigin(origins = "*")
public class CartController {
	@Autowired
	private CartService cartService;

	@GetMapping
	public List<CartData> getAllCart() {
		return cartService.findAll();
	}

	@GetMapping("{id}")
	public CartData findById(@PathVariable Long id) {
		return cartService.findById(id);

	}

	@PostMapping
	public CartData create(@RequestBody CartData cartData) {
		return cartService.create(cartData);
	}

	@DeleteMapping("{id}")
	public boolean delete(@PathVariable Long id) {
		return cartService.delete(id);
	}

	@PutMapping("{id}")
	public boolean update(@RequestBody CartData cartData, @PathVariable("id") Long id) {
		return cartService.update(cartData, id);
	}
}
