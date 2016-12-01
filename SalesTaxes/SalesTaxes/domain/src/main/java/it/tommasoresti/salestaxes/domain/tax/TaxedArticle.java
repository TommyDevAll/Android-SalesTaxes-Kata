package it.tommasoresti.salestaxes.domain.tax;

import java.math.BigDecimal;

import it.tommasoresti.salestaxes.domain.article.ArticleWrapper;

public class TaxedArticle extends ArticleWrapper {
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
