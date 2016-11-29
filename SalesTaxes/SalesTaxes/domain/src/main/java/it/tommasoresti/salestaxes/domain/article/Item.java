package it.tommasoresti.salestaxes.domain.article;

public class Item implements Article{

    private String description;
    private String category;
    private float price;

    public Item(String category, String description, float price) {
        this.category = category;
        this.description = description;
        this.setPrice(price);
    }

    public String getCategory() {
        return category;
    }

    @Override
    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public float getPrice() {
        return price;
    }
}
