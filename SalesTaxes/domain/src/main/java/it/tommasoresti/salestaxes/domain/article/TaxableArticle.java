package it.tommasoresti.salestaxes.domain.article;

import java.math.BigDecimal;

public class TaxableArticle extends ArticleDecorator {

    private BigDecimal taxesPercentage = new BigDecimal(0);

    public TaxableArticle(Article article) {
        super(article);
    }
    public TaxableArticle(Article article, BigDecimal taxesPercentage) {
        super(article);
        this.taxesPercentage = taxesPercentage;
    }

    public TaxableArticle addTaxPercentage(BigDecimal taxesPercentage) {
        return new TaxableArticle(getArticle(), this.taxesPercentage.add(taxesPercentage));
    }

    public BigDecimal getTaxesPercentage() {
        return taxesPercentage;
    }
}
