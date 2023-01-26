package com.pawfor.shoppingCart.mapper;

import com.pawfor.shoppingCart.domain.Cart;
import com.pawfor.shoppingCart.domain.CartDto;
import com.pawfor.shoppingCart.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartMapper {

    private final DbService service;
    private final ItemMapper itemMapper;

    public Cart mapToCart(final CartDto cartDto) {
        return new Cart(
                cartDto.getCartId()
        );
    }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(
                cart.getCartId(),
                itemMapper.mapToItemDtoList(service.itemsInCart(cart.getCartId())),
                service.getTotalValue(service.itemsInCart(cart.getCartId()))
        );
    }
}
