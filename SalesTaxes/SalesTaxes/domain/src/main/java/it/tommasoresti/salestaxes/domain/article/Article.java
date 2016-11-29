package it.tommasoresti.salestaxes.domain.article;

import java.math.BigDecimal;

public interface Article {
    String getCategory();
    void setPrice(BigDecimal price);
    BigDecimal getPrice();
}
