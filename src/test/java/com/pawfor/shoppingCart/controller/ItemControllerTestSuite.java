package com.pawfor.shoppingCart.controller;

import com.pawfor.shoppingCart.domain.ItemDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringJUnitWebConfig
@WebMvcTest(ItemController.class)
class ItemControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemController itemController;

    @Test
    void shouldCreateItem() throws Exception {
        //Given
        ItemDto itemDto1 = new ItemDto(28L, "Test", 100);

        when(itemController.createItem(itemDto1)).thenReturn(ResponseEntity.ok().build());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/items")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    void shouldRemoveItem() throws Exception {
        //Given
        ItemDto itemDto1 = new ItemDto(28L, "Test", 100);

        when(itemController.removeItem(itemDto1.getItemId())).thenReturn(ResponseEntity.ok().build());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/items")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateItem() throws Exception {
        //Given
        List<ItemDto> itemDtoList = List.of(new ItemDto(1L, "test item", 100),
                new ItemDto(2L, "test item2", 100));
        ItemDto itemDto1 = new ItemDto(1L, "Test updated", 100);

        when(itemController.updateItem(itemDtoList.get(0))).thenReturn(ResponseEntity.ok(itemDto1));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/items")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldGetItems() throws Exception {
        //Given
        List<ItemDto> itemDtoList = List.of(new ItemDto(1L, "test item", 100),
                new ItemDto(2L, "test item2", 100));

        when(itemController.getItems(Pageable.ofSize(10))).thenReturn(ResponseEntity.ok(itemDtoList));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/items")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));



    }

}