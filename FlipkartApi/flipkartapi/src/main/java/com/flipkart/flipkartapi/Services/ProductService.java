package com.flipkart.flipkartapi.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flipkart.flipkartapi.dao.ProductRepository;
import com.flipkart.flipkartapi.dto.ProductData;
import com.flipkart.flipkartapi.exception.IdNotFoundException;
import com.flipkart.flipkartapi.model.Product;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    private Product getProductEntityProduct(ProductData productData) {
        Product product = new Product();
        product.setId(productData.getProductId());
        product.setName(productData.getName());
        product.setDescription(productData.getDescription());
        product.setCategory(productData.getCategory());
        product.setPrice(productData.getPrice());
        product.setColor(productData.getColor());
        product.setImage(productData.getImage());
        return product;

    }

    private ProductData geProductData(Product product) {
        ProductData productData = new ProductData();
        productData.setProductId(product.getId());
        productData.setName(product.getName());
        productData.setCategory(product.getCategory());
        productData.setDescription(product.getDescription());
        productData.setColor(product.getColor());
        productData.setPrice(product.getPrice());
        productData.setImage(product.getImage());
        return productData;
    }

    @Override
    public List<ProductData> findAll() {
        List<ProductData> productDataList = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        products.forEach(product -> {
            productDataList.add(geProductData(product));
        });

        return productDataList;
    }

    @Override
    public ProductData findById(Long id) throws IdNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional == null) {
            new IdNotFoundException("Product Not Found");
        }
        return geProductData(productOptional.get());

    }

    @Override
    public ProductData create(ProductData productData) {
        Product product = getProductEntityProduct(productData);
        return geProductData(productRepository.save(product));
    }

    @Override
    public boolean delete(Long id) {
        Product product = productRepository.findById(id).get();
        if (product != null) {
            productRepository.deleteById(id);
            return true;

        }
        return false;
    }

    @Override
    public boolean update(ProductData productData, Long id) {
        Product product2 = productRepository.findById(id).get();
        if (product2 != null) {
            product2.setName(productData.getName());
            product2.setCategory(productData.getCategory());
            product2.setPrice(productData.getPrice());
            product2.setColor(productData.getColor());
            product2.setImage(productData.getImage());
            product2.setDescription(productData.getDescription());
            productRepository.save(product2);
            return true;

        } else {
            return false;

        }

    }

}
