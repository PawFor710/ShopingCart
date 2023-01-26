package com.pawfor.shoppingCart.controller;


import com.pawfor.shoppingCart.domain.Cart;
import com.pawfor.shoppingCart.domain.CartDto;
import com.pawfor.shoppingCart.mapper.CartMapper;
import com.pawfor.shoppingCart.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final DbService service;
    private final CartMapper cartMapper;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
        service.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{cartId}/item/{itemId}/add")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable Long itemId,
                                              @PathVariable Long cartId)
            throws CartNotFoundException, ItemNotFoundException, TooMuchItemsException, ItemAlreadyInCartException {
        service.addItemToCart(itemId, cartId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{cartId}/item/{itemId}/remove")
    public ResponseEntity<CartDto> removeItemFromCart(@PathVariable Long itemId,
                                                 @PathVariable Long cartId)
            throws CartNotFoundException, ItemNotFoundException, ItemIsNotInCartException {
        service.removeItemFromCart(itemId, cartId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCartItems(@PathVariable Long cartId) throws CartNotFoundException {
        Cart cart = service.getCartById(cartId);
        return ResponseEntity.ok(cartMapper.mapToCartDto(cart));
    }
}
