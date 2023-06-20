package com.product.services.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.services.dbo.Product;
import com.product.services.dto.ProductInDto;
import com.product.services.dto.ProductOutDto;
import com.product.services.exception.BadRequestException;
import com.product.services.exception.NotFoundException;
import com.product.services.service.ProductService;
import com.product.services.validator.ProductValidator;

import jakarta.validation.Valid;

@RestController
public class ProductController {

	/**
	 * The Logger object.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	/**
	 * The productService object.
	 */
	@Autowired
	private ProductService productService;

	/**
	 * The productValidator object.
	 */
	@Autowired
	private ProductValidator productValidator;

	/** This function is used to fetch all the product details present in database.
	 * @return The details of all the products present in database.
	 * @throws NotFoundException
	 */
	@GetMapping("/fetchProduct")
	public ProductOutDto getAllProducts() throws NotFoundException {
		LOGGER.info("Request received to fetch the product");
		ProductOutDto productOutDto = productService.getAllProducts();
		LOGGER.info("Successfully fetched the product details {}", productOutDto.toString());
		return productOutDto;
	}

	/** This function is used to fetch the details of product on the basis of product Id.
	 * @param productId
	 * @return the details of product for particular productId.
	 * @throws Exception
	 */
	@GetMapping("/fetchProduct/{productId}")
	public Product getProductById(@PathVariable Long productId) throws Exception {
		LOGGER.info("Request received to fetch the product by id {}", productId);
		productValidator.validateProductId(productId);
		Product product = productService.getProductById(productId);
		LOGGER.info("Successfully fetched product details for id {}", productId);
		return product;
	}

	/** This function is used to add new product in database.
	 * @param product
	 * @return the details of newly added product in database.
	 * @throws BadRequestException
	 */
	@PostMapping("/addProduct")
	public Product addNewProduct(@Valid @RequestBody Product product) throws BadRequestException {
		LOGGER.info("Request received to add the product for {}", product.toString());
		Product addedProductDetail = productService.addNewProduct(product);
		LOGGER.info("Successfully added product details for {}", product.toString());
		return addedProductDetail;
	}

	/** This product is used to update the product details.
	 * @param productId
	 * @param productInDto
	 * @return the details of updated product.
	 * @throws Exception
	 */
	@PutMapping("/{productId}")
	public Product updateProduct(@PathVariable Long productId, @Valid @RequestBody ProductInDto productInDto) throws Exception {
		LOGGER.info("Request received to update the product for {}", productInDto.toString());
		productValidator.validateProductId(productId);
		Product updatedProductDetail = productService.updateProduct(productId, productInDto);
		LOGGER.info("Successfully updated product details for {}", productInDto.toString());
		return updatedProductDetail;
	}

	/** This function is used to delete the product details.
	 * @param productId
	 * @return the status of deleted product.
	 * @throws Exception
	 */
	@DeleteMapping("/{productId}")
	public String deleteProduct(@PathVariable Long productId) throws Exception {
		LOGGER.info("Request received to delete the product for id {}", productId);
		productValidator.validateProductId(productId);
		productService.deleteProduct(productId);
		return String.format("Product details deleted successfully for id %s", productId);
	}
}
