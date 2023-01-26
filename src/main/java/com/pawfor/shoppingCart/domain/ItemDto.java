package com.pawfor.shoppingCart.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private Long itemId;
    private String title;
    private int price;
}
