package it.tommasoresti.salestaxes.domain.article;

public abstract class Item extends Article{

    private String description;

    public Item(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Item getItem() {
        return this;
    }
}
