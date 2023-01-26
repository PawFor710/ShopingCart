package com.pawfor.shoppingCart.mapper;

import com.pawfor.shoppingCart.domain.Item;
import com.pawfor.shoppingCart.domain.ItemDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemMapper {

    public Item mapToItem(final ItemDto itemDto) {
        return new Item(
                itemDto.getItemId(),
                itemDto.getTitle(),
                itemDto.getPrice()
        );
    }

    public ItemDto mapToItemDto(final Item item) {
        return new ItemDto(
                item.getItemId(),
                item.getTitle(),
                item.getPrice()
        );
    }

    public List<ItemDto> mapToItemDtoList(final List<Item> itemList) {
        return itemList.stream()
                .map(this::mapToItemDto)
                .collect(Collectors.toList());
    }
}
