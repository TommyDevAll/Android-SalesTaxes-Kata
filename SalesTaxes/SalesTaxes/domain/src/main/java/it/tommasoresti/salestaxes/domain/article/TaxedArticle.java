package it.tommasoresti.salestaxes.domain.article;

import java.math.BigDecimal;

import it.tommasoresti.salestaxes.domain.round.RoundingPolicy;

public class TaxedArticle extends ArticleDecorator {
    private BigDecimal finalPrice;

    private TaxedArticle(TaxableArticle article, BigDecimal finalPrice) {
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

    public static class Factory {

        private RoundingPolicy roundingPolicy;

        public Factory(RoundingPolicy roundingPolicy) {
            this.roundingPolicy = roundingPolicy;
        }

        public TaxedArticle make(TaxableArticle article) {
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
}
