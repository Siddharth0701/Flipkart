package com.flipkart.flipkartapi.dto;

import java.util.List;
import com.flipkart.flipkartapi.model.Product;
import com.flipkart.flipkartapi.model.Users;

public class CartData {
	private Long id;

	private Users users;

	private List<Product> product;

	public CartData() {
		super();
		//TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
	

}
