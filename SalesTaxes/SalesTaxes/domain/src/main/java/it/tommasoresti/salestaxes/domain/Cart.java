package it.tommasoresti.salestaxes.domain;

import java.util.ArrayList;
import java.util.List;

import it.tommasoresti.salestaxes.domain.item.Item;

public class Cart {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        this.items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }
}
