package sample.model;

import java.util.List;

public record Cart(List<Item> items, int userId) {

}
