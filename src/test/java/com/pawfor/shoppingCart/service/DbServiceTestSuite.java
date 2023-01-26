package com.pawfor.shoppingCart.service;

import com.pawfor.shoppingCart.controller.CartNotFoundException;
import com.pawfor.shoppingCart.controller.ItemAlreadyInCartException;
import com.pawfor.shoppingCart.controller.ItemNotFoundException;
import com.pawfor.shoppingCart.controller.TooMuchItemsException;
import com.pawfor.shoppingCart.domain.Cart;
import com.pawfor.shoppingCart.domain.Item;
import com.pawfor.shoppingCart.repository.CartRepository;
import com.pawfor.shoppingCart.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DbServiceTestSuite {

    @Autowired
    private DbService dbService;

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    void getAllItemsTest() {
        //Given
        Item item1 = new Item(1L, "Test item", 100);
        Item item2 = new Item(2L, "Test item", 100);
        Item item3 = new Item(3L, "Test item", 100);

        dbService.saveItem(item1);
        dbService.saveItem(item2);
        dbService.saveItem(item3);

        //When
        List<Item> result = dbService.getAllItems(Pageable.ofSize(10));

        //Then
        assertEquals(result.size(), 3);

        //Cleaning
        itemRepository.deleteAll();
    }

    @Test
    void saveItemTest() {
        //Given
        Item item1 = new Item(1L, "Test item", 100);
        dbService.saveItem(item1);

        //When
        List<Item> items = dbService.getAllItems(Pageable.ofSize(10));
        String result = items.get(0).getTitle();

        //Then
        assertEquals(result, item1.getTitle());

        //Cleaning
        itemRepository.deleteAll();

    }

    @Test
    void deleteItemTest() {
        //Given
        Item item1 = new Item(22L, "Test item", 100);
        dbService.saveItem(item1);
        dbService.deleteItem(item1.getItemId());

        //When
        List<Item> items = dbService.getAllItems(Pageable.ofSize(10));

        //Then
        assertEquals(items.size(), 0);

    }

    @Test
    void itemsInCartTest() throws ItemAlreadyInCartException, CartNotFoundException, TooMuchItemsException, ItemNotFoundException {
        //Given
        Item item1 = new Item(25L, "Test item", 100);
        Cart cart1 = new Cart(26L);

        dbService.saveItem(item1);
        dbService.saveCart(cart1);

        dbService.addItemToCart(item1.getItemId(), cart1.getCartId());

        //When
        List<Item> items = dbService.itemsInCart(cart1.getCartId());

        //Then
        assertEquals(items.get(0).getTitle(), item1.getTitle());

        //Cleaning
        itemRepository.deleteAll();
        cartRepository.deleteAll();
    }

    @Test
    void saveCartAndGetByIdTest() throws CartNotFoundException {
        //Given
        Cart cart1 = new Cart(27L);

        dbService.saveCart(cart1);

        //When
        Cart carts = dbService.getCartById(cart1.getCartId());

        //Then
        assertEquals(carts.getCartId(), cart1.getCartId());

        //Cleaning
        cartRepository.deleteAll();
    }

    @Test
    void getTotalValueTest() {
        //Given
        Item item1 = new Item(1L, "Test item", 100);
        Item item2 = new Item(2L, "Test item", 100);
        Item item3 = new Item(3L, "Test item", 100);

        List<Item> items = List.of(item1, item2, item3);

        //When
        int result = dbService.getTotalValue(items);

        //Then
        assertEquals(300, result);
    }
}