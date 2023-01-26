package com.pawfor.shoppingCart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<Object> handleCartNotFoundException(CartNotFoundException e) {
        return new ResponseEntity<>("Cart with given id does not exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleItemNNotFoundException(ItemNotFoundException e) {
        return new ResponseEntity<>("Item with given id does not exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleTooMuchItemsException(TooMuchItemsException e) {
        return new ResponseEntity<>("You can not add more the 3 items to your cart", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleItemAlreadyInCart(ItemAlreadyInCartException e) {
        return new ResponseEntity<>("This item is already in cart", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleItemIsNotInCart(ItemIsNotInCartException e) {
        return new ResponseEntity<>("This item is not in this cart", HttpStatus.BAD_REQUEST);
    }



}
