package com.flipkart.flipkartapi.controller;

import java.util.List;

import com.flipkart.flipkartapi.Services.SellerService;
import com.flipkart.flipkartapi.dto.SellerData;
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
@RequestMapping("api/seller")
@CrossOrigin(origins = "*")
public class SellerController {
	@Autowired
	private SellerService sellerService;

	@GetMapping
	public List<SellerData> findAll() {
		return sellerService.findAll();

	}

	@GetMapping("{id}")
	public SellerData findById(@PathVariable Long id) {
		return sellerService.findById(id);
	}

	@PostMapping
	public SellerData create(@RequestBody SellerData sellerData) {
		return sellerService.create(sellerData);
	}

	@DeleteMapping("{id}")
	public boolean delete(@PathVariable Long id) {
		return sellerService.delete(id);
	}

	@PutMapping("{id}")
	public boolean updateProduct(@RequestBody SellerData sellerData, @PathVariable("id") Long sellerId) {
		return sellerService.update(sellerData, sellerId);

	}

}
