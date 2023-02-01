package com.flipkart.flipkartapi.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.flipkart.flipkartapi.dao.SellorRepository;
import com.flipkart.flipkartapi.dto.SellerData;
import com.flipkart.flipkartapi.exception.IdNotFoundException;
import com.flipkart.flipkartapi.model.Seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService implements ISellerService {
	@Autowired
	private SellorRepository sellorRepository;

	private Seller getSellerEntity(SellerData sellerData) {
		Seller seller = new Seller();
		seller.setId(sellerData.getId());
		seller.setName(sellerData.getName());
		seller.setEmail(sellerData.getEmail());
		seller.setPassword(sellerData.getPassword());
		return seller;

	}

	private SellerData getSellerData(Seller seller) {
		SellerData sellerData = new SellerData();
		sellerData.setId(seller.getId());
		sellerData.setName(seller.getName());
		sellerData.setEmail(seller.getEmail());
		sellerData.setPassword(seller.getPassword());
		return sellerData;

	}

	@Override
	public List<SellerData> findAll() {
		List<SellerData> sellerDataList = new ArrayList<>();
		List<Seller> sellers = sellorRepository.findAll();
		sellers.forEach(seller -> {
			sellerDataList.add(getSellerData(seller));
		});

		return sellerDataList;
	}

	@Override
	public SellerData findById(Long id) throws IdNotFoundException {
		Optional<Seller> sellerOptional = sellorRepository.findById(id);
		if (sellerOptional == null) {
			new IdNotFoundException("Seller id not found");

		}

		return getSellerData(sellerOptional.get());
	}

	@Override
	public SellerData create(SellerData sellerData) {
		Seller seller = getSellerEntity(sellerData);
		return getSellerData(sellorRepository.save(seller));
	}

	@Override
	public boolean delete(Long id) {
		Seller seller = sellorRepository.findById(id).get();
		if (seller != null) {
			sellorRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean update(SellerData sellerData, Long id) {
		Seller seller2 = sellorRepository.findById(id).get();
		if (seller2 != null) {
			seller2.setName(sellerData.getName());
			seller2.setEmail(sellerData.getEmail());
			seller2.setPassword(sellerData.getPassword());
			sellorRepository.save(seller2);
			return true;

		}

		return false;
	}

}
