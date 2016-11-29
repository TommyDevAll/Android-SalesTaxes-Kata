package it.tommasoresti.salestaxes.domain.article;

public class Item extends Article{

    private String description;
    private String category;

    public Item(String category, String description, float price) {
        this.category = category;
        this.description = description;
        this.setPrice(price);
    }

    public Item(String category, float price) {
        this(category, "", price);
    }

    public Item getItem() {
        return this;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }
}
