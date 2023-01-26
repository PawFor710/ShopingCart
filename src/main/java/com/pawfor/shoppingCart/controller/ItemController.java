package com.pawfor.shoppingCart.controller;

import com.pawfor.shoppingCart.domain.Item;
import com.pawfor.shoppingCart.domain.ItemDto;
import com.pawfor.shoppingCart.mapper.ItemMapper;
import com.pawfor.shoppingCart.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/items")
public class ItemController {

    public static final int PAGE_SIZE = 3;
    private final DbService service;
    private final ItemMapper mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createItem(@RequestBody ItemDto itemDto) {
        Item item = mapper.mapToItem(itemDto);
        service.saveItem(item);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long itemId) {
        service.deleteItem(itemId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<ItemDto> updateItem(@RequestBody ItemDto itemDto) {
        Item item = mapper.mapToItem(itemDto);
        Item savedItem = service.saveItem(item);
        return ResponseEntity.ok(mapper.mapToItemDto(savedItem));
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getItems(@PageableDefault(size = PAGE_SIZE) Pageable pageable) {
        List<Item> items = service.getAllItems(pageable);
        return ResponseEntity.ok(mapper.mapToItemDtoList(items));
    }
}
