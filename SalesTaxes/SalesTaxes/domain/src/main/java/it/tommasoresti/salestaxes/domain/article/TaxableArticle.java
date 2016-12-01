package it.tommasoresti.salestaxes.domain.article;

import java.math.BigDecimal;

public class TaxableArticle extends ArticleDecorator {

    private BigDecimal taxesPercentage = new BigDecimal(0);

    public TaxableArticle(Article article) {
        super(article);
    }

    public void addTaxPercentage(BigDecimal taxesPercentage) {
        this.taxesPercentage = this.taxesPercentage.add(taxesPercentage);
    }

    public BigDecimal getTaxesPercentage() {
        return taxesPercentage;
    }
}
