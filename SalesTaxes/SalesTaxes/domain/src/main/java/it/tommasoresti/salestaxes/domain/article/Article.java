package it.tommasoresti.salestaxes.domain.article;

public abstract class Article {
    private float price;

    public abstract Item getItem();

    public void setPrice(float price) {
        this.price = price;
    }
}
