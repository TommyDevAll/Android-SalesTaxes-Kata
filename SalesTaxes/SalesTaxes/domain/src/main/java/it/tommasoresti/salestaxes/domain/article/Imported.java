package it.tommasoresti.salestaxes.domain.article;

import java.math.BigDecimal;

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
    public void setPrice(BigDecimal price) {
        item.setPrice(price);
    }

    @Override
    public BigDecimal getPrice() {
        return item.getPrice();
    }

    @Override
    public String getDescription() {
        return "imported " + item.getDescription();
    }
}
