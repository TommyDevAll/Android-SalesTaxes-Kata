package it.tommasoresti.salestaxes.domain.item;

public abstract class Item {

    private String description;

    public Item(String description) {
        this.description = description;
    }

    public abstract ItemType getType();

    public String getDescription() {
        return description;
    }
}
