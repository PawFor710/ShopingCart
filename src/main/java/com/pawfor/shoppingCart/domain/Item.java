package com.pawfor.shoppingCart.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "shop_items")
public class Item {

    @Id
    @GeneratedValue
    private Long itemId;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private int price;

    @ManyToOne
    private Cart cart;

    public Item(Long itemId, String title, int price) {
        this.itemId = itemId;
        this.title = title;
        this.price = price;
    }
}
