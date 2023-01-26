package com.pawfor.shoppingCart.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private Long cartId;
    private List<ItemDto> items;
    private int totalValue;
}
