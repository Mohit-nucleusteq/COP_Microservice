package com.cart.services.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.services.dbo.CartItem;
import com.cart.services.exception.BadRequestException;
import com.cart.services.exception.NotFoundException;
import com.cart.services.repository.CartRepository;

/** This class contains the business logic of cart services.
 *
 */
@Service
public class CartService {

    /**
     * The cartRepository object.
     */
    @Autowired
    private CartRepository cartRepository;

    /**
     * The sequenceGenerator.
     */
    @Autowired
    private SequenceGenerator sequenceGenerator;

    /**
     * @param cartItem
     * @return the saved items in db.
     * @throws BadRequestException 
     */
    public CartItem addToCart(CartItem cartItem) throws BadRequestException {
        if(cartItem.getProductName().isEmpty()) {
            throw new BadRequestException("Item cannot be null or empty");
        }
        cartItem.setId(sequenceGenerator.generateSequence(cartItem.SEQUENCE_NAME));
        return cartRepository.save(cartItem);
    }


    /**
     * @param id - item id.
     * @param updatedCartItem - items needs to be update.
     * @return return updated cart item.
     * @throws NotFoundException.
     */
    public CartItem updateCartItem(Long id, CartItem updatedCartItem) throws NotFoundException {
        Optional<CartItem> cartItemOptional = cartRepository.findById(id);

        if(!cartItemOptional.isPresent()) {
            throw new NotFoundException(String.format("Items not found in database for id {}", id));
        }
        CartItem cartItem = cartItemOptional.get();
        cartItem.setProductName(updatedCartItem.getProductName());
        cartItem.setQuantity(updatedCartItem.getQuantity());
        return cartRepository.save(cartItem);
    }


    /** This method is used to delete the cart item.
     * @param id - id of the item.
     * @throws NotFoundException.
     */
    public void deleteCartItem(Long id) throws NotFoundException {
        Optional<CartItem> cartItemOptional = cartRepository.findById(id);

        if(!cartItemOptional.isPresent()) {
            throw new NotFoundException(String.format("Items not found in database for id {}", id));
        }
        cartRepository.deleteById(id);
    }


    /** This method is used to return all the cart items.
     * @return all the cart items.
     */
    public List<CartItem> getAllCartItems() {
        return cartRepository.findAll();
    }


}
