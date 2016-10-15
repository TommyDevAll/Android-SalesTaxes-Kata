package it.tommasoresti.salestaxes.domain.item;

public class Book extends Item {
    public Book(String description) {
        super(description);
    }

    @Override
    public ItemType getType() {
        return ItemType.BOOKS;
    }
}
