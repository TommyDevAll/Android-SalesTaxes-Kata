package it.tommasoresti.salestaxes.domain.article;

import java.math.BigDecimal;

public class TaxedArticle extends ArticleDecorator {
    private BigDecimal finalPrice;

    public TaxedArticle(TaxableArticle article, BigDecimal finalPrice) {
        super(article);
        this.finalPrice = finalPrice;
    }

    @Override
    public BigDecimal getPrice() {
        return finalPrice;
    }

    public BigDecimal getNonTaxedPrice() {
        return super.getPrice();
    }
}
