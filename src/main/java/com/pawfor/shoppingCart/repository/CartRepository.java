package com.pawfor.shoppingCart.repository;

import com.pawfor.shoppingCart.domain.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    Optional<Cart> findByCartId(Long cartId);

}
