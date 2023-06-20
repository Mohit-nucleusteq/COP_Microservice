package com.product.services.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.services.dbo.Product;
import com.product.services.dto.ProductInDto;
import com.product.services.dto.ProductOutDto;
import com.product.services.exception.NotFoundException;
import com.product.services.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
    private SequenceGenerator sequenceGenerator;

	public ProductOutDto getAllProducts() throws NotFoundException {
		ProductOutDto productOutDto = new ProductOutDto();

		List<Product> productDetailsList =  productRepository.findAll();
		if(Objects.isNull(productDetailsList)) {
			throw new NotFoundException("Product details are not present in database");
		}
		productOutDto.setProductDetails(productDetailsList);
		Long totalProduct = productRepository.count();
		productOutDto.setCount(totalProduct);
		return productOutDto;
	}

	public Product getProductById(Long productId) throws NotFoundException {

		Optional<Product> productDetails = productRepository.findById(productId);
		Product product = productDetails.get();
		return product;
	}

	public Product addNewProduct(Product product) {
		product.setProductId(sequenceGenerator.generateSequence(product.SEQUENCE_NAME));
		return productRepository.save(product);
	}

	public Product updateProduct(Long productId, ProductInDto productInDto) throws Exception {
		Product existingProduct = getProductById(productId);
		existingProduct.setProductName(productInDto.getProductName());
		existingProduct.setDescription(productInDto.getDescription());
		existingProduct.setPrice(productInDto.getPrice());
		existingProduct.setQuantity(productInDto.getQuantity());
		existingProduct.setDiscount(productInDto.getDiscount());
		existingProduct.setManufacturedBy(productInDto.getManufacturedBy());
		return productRepository.save(existingProduct);
	}

	public String deleteProduct(Long productId) throws Exception {
		Product existingProduct = getProductById(productId);
		productRepository.delete(existingProduct);
		return String.format("Product with id %s has been deleted successfully", productId);
	}

}
