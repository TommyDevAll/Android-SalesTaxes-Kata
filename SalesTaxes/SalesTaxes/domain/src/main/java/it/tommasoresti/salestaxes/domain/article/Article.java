package it.tommasoresti.salestaxes.domain.article;

import java.math.BigDecimal;

public interface Article {
    String getCategory();
    String getDescription();
    BigDecimal getPrice();
}
