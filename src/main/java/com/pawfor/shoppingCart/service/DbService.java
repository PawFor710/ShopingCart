package com.pawfor.shoppingCart.service;

import com.pawfor.shoppingCart.controller.*;
import com.pawfor.shoppingCart.domain.Cart;
import com.pawfor.shoppingCart.domain.Item;
import com.pawfor.shoppingCart.repository.CartRepository;
import com.pawfor.shoppingCart.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DbService {


    //Item
    private final ItemRepository itemRepository;

    public List<Item> getAllItems(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public Item saveItem(final Item item) {
        return itemRepository.save(item);
    }

    public void deleteItem(final Long itemId) {
        itemRepository.deleteById(itemId);
    }

    public List<Item> itemsInCart(Long cartId) {
        return itemRepository.findAllByCart_CartId(cartId);
    }


    //Cart
    private final CartRepository cartRepository;

    public void saveCart(final Cart cart) {
        cartRepository.save(cart);
    }


    @Transactional
    public void addItemToCart(Long itemId, Long cartId) throws TooMuchItemsException,
            CartNotFoundException, ItemNotFoundException, ItemAlreadyInCartException {

        Cart cart = cartRepository.findByCartId(cartId).orElseThrow(CartNotFoundException::new);
        Item item = itemRepository.findByItemId(itemId).orElseThrow(ItemNotFoundException::new);


        if (Objects.nonNull(item.getCart())) throw new ItemAlreadyInCartException();
        if (cart.getItems().size() >= 3) throw new TooMuchItemsException();

        cart.addItem(item);
        item.setCart(cart);
    }

    @Transactional
    public void removeItemFromCart(Long itemId, Long cartId) throws CartNotFoundException,
            ItemNotFoundException, ItemIsNotInCartException {

        Cart cart = cartRepository.findByCartId(cartId).orElseThrow(CartNotFoundException::new);
        Item item = itemRepository.findByItemId(itemId).orElseThrow(ItemNotFoundException::new);

        if (!cart.getItems().contains(item)) throw new ItemIsNotInCartException();
        cart.removeItem(item);
        item.setCart(null);

    }

    public Cart getCartById(Long cartId) throws CartNotFoundException {

        return cartRepository.findByCartId(cartId).orElseThrow(CartNotFoundException::new);
    }

    public int getTotalValue(List<Item> items) {
        int totalPrice = 0;
        for (Item item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
}
