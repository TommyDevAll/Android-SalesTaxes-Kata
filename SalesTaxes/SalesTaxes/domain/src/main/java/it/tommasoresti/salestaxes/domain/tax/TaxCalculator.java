package it.tommasoresti.salestaxes.domain.tax;

import java.math.BigDecimal;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.TaxableArticle;
import it.tommasoresti.salestaxes.domain.article.TaxedArticle;
import it.tommasoresti.salestaxes.domain.round.RoundingPolicy;

public class TaxCalculator {

    private RoundingPolicy roundingPolicy;

    public TaxCalculator(RoundingPolicy roundingPolicy) {
        this.roundingPolicy = roundingPolicy;
    }

    public TaxedArticle calculate(TaxableArticle article) {
        BigDecimal taxesPaid = percentageOf(article, article.getTaxesPercentage());
        return new TaxedArticle(article, article.getPrice().add(roundingPolicy.round(taxesPaid)));
    }

    private BigDecimal percentageOf(Article article, BigDecimal taxes) {
        BigDecimal price = article.getPrice();
        BigDecimal multiply = price.multiply(taxes);
        BigDecimal divide = multiply.divide(new BigDecimal(100));
        return divide;
    }
}
