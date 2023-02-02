package com.flipkart.flipkartapi.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.flipkart.flipkartapi.dao.CartRepository;
import com.flipkart.flipkartapi.dto.CartData;
import com.flipkart.flipkartapi.exception.IdNotFoundException;
import com.flipkart.flipkartapi.model.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService {

	@Autowired
	private CartRepository cartRepository;

	private Cart getCartEntity(CartData cartData) {
		Cart cart = new Cart();
		cart.setId(cartData.getId());
		cart.setUsers(cartData.getUsers());
		cart.setProduct(cartData.getProduct());
		return cart;

	}

	private CartData getCartData(Cart cart) {
		CartData cartData = new CartData();
		cartData.setId(cart.getId());
		cartData.setUsers(cart.getUsers());
		cartData.setProduct(cart.getProduct());
		return cartData;
	}

	@Override
	public List<CartData> findAll() {
		List<CartData> cartDataList = new ArrayList<>();
		List<Cart> cart = cartRepository.findAll();
		cart.forEach(carts -> {
			cartDataList.add(getCartData(carts));
		});
		return cartDataList;
	}

	@Override
	public CartData findById(Long id) throws IdNotFoundException {
		Optional<Cart> cartOptional = cartRepository.findById(id);
		if (cartOptional == null) {
			new IdNotFoundException("Cart id not found");

		}

		return getCartData(cartOptional.get());
	}

	@Override
	public CartData create(CartData cartData) {
		Cart cart = getCartEntity(cartData);
		return getCartData(cartRepository.save(cart));
	}

	@Override
	public boolean delete(Long id) {
		Cart cart = cartRepository.findById(id).get();
		if (cart != null) {
			cartRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean update(CartData cartData, Long id) {
		Cart cart = cartRepository.findById(id).get();
		if (cart != null) {
			cart.setUsers(cartData.getUsers());
			cart.setProduct(cartData.getProduct());
			cartRepository.save(cart);
			return true;

		}

		return false;
	}

}
