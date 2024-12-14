package com.product_Category_Management.service;

import com.product_Category_Management.entity.Product;
import com.product_Category_Management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	// Method to get all products with pagination
	public List<Product> getAllProducts(int page, int size) {
		return productRepository.findAll(PageRequest.of(page, size)).getContent();
	}

	// Method to get a product by its ID
	public Product getProductById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
	}

	// Method to create a new product
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	// Method to update an existing product
	public Product updateProduct(Long id, Product updatedProduct) {
		Product existingProduct = getProductById(id);
		existingProduct.setName(updatedProduct.getName());
		existingProduct.setCategory(updatedProduct.getCategory());
		return productRepository.save(existingProduct);
	}

	// Method to delete a product by its ID
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}