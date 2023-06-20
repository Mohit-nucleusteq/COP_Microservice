package com.product.services.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.product.services.dbo.Product;
import com.product.services.exception.NotFoundException;
import com.product.services.repository.ProductRepository;

@Component
public class ProductValidator {

	/**
	 * The productRepository object.
	 */
	@Autowired
	private ProductRepository productRepository;

	/** This function is used to check productId is present or not in database.
	 * @param productId
	 * @throws NotFoundException
	 */
	public void validateProductId(Long productId) throws NotFoundException {

		Optional<Product> productDetails = productRepository.findById(productId);
		if (!productDetails.isPresent()) {
			throw new NotFoundException("Product details not found for productId " + productId);
		}
	}

}
