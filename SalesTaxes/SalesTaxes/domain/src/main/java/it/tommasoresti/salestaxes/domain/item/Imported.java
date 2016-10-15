package it.tommasoresti.salestaxes.domain.item;

public class Imported extends Item {
    private Item item;

    public Imported(Item item) {
        super("Imported "+ item.getDescription());
        this.item = item;
    }

    @Override
    public ItemType getType() {
        return item.getType();
    }
}
