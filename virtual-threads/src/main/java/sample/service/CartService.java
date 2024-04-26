package sample.service;

import java.util.List;

import sample.model.Cart;
import sample.model.Item;

public class CartService {

  public Cart findCartByUserId(int userId) {
    return new Cart(List.of(new Item(42, "Vogon blaster")), userId);
  }
}
