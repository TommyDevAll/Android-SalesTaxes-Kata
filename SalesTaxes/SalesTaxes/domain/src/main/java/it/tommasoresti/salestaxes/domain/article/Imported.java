package it.tommasoresti.salestaxes.domain.article;

public class Imported extends Article {
    private Item item;

    public Imported(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
