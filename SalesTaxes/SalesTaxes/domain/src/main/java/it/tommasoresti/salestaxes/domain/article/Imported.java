package it.tommasoresti.salestaxes.domain.article;

public class Imported implements Article {
    private Item item;

    public Imported(Item item) {
        this.item = item;
    }

    @Override
    public String getCategory() {
        return item.getCategory();
    }

    @Override
    public void setPrice(float price) {
        item.setPrice(price);
    }

    @Override
    public float getPrice() {
        return item.getPrice();
    }
}
