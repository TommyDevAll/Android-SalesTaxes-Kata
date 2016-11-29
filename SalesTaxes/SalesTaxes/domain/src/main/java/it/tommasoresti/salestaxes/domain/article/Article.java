package it.tommasoresti.salestaxes.domain.article;

public interface Article {
    String getCategory();
    void setPrice(float price);
    float getPrice();
}
