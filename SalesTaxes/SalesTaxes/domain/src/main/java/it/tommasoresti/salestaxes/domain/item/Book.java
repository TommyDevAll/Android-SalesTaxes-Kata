package it.tommasoresti.salestaxes.domain.item;

public class Book extends Item {
    @Override
    public ItemType getType() {
        return ItemType.BOOKS;
    }
}
