package ru.gb.springdatajpa.model;

import lombok.Data;
import ru.gb.springdatajpa.dto.CartItem;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<CartItem> items = new ArrayList<>();
    private double price;

    public void addItem(CartItem cartItem) {
        boolean flag = true;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProductId().equals(cartItem.getProductId())) {
                items.get(i).incrementCount();
                flag = false;
            }
        }
        if (flag) items.add(cartItem);
        recalculate();
    }

    public void removeItem(Long id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProductId().equals(id)) {
                items.get(i).decrementCount();
                if (items.get(i).getCount() == 0) items.remove(items.get(i));
            }
        }
        recalculate();
    }
    private void recalculate() {
        price = 0;
        for (int i = 0; i < items.size(); i++) {
            price = price + items.get(i).getPrice();
        }
    }
}
