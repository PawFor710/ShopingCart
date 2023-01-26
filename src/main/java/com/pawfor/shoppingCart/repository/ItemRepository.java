package com.pawfor.shoppingCart.repository;

import com.pawfor.shoppingCart.domain.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> findAll(Pageable pageable);

    Optional<Item> findByItemId(Long itemId);

    List<Item> findAllByCart_CartId(Long cartId);
}
