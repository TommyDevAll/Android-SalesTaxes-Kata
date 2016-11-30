package it.tommasoresti.salestaxes.domain.article;

import java.math.BigDecimal;

public class TaxableArticle implements Article {
    private Article article;
    private BigDecimal taxesPercentage = new BigDecimal(0);
    private BigDecimal finalPrice = new BigDecimal(0);

    public TaxableArticle(Article article) {
        this.article = article;
        this.finalPrice = article.getPrice();
    }

    private void updateFinalPrice() {
        BigDecimal taxesPaid = percentageOf(article, taxesPercentage);
        this.finalPrice = article.getPrice().add(round5Cents(taxesPaid));
    }

    private static BigDecimal round5Cents(BigDecimal value) {
        BigDecimal step = new BigDecimal("0.05");
        BigDecimal division = value.divide(step, BigDecimal.ROUND_UP);
        BigDecimal integerDivision = division.setScale(0, BigDecimal.ROUND_UP);
        BigDecimal valueToNearestStep = step.multiply(integerDivision.subtract(division));
        value = value.add(valueToNearestStep);
        value = value.setScale(2, BigDecimal.ROUND_UP);
        return value;
    }

    private BigDecimal percentageOf(Article article, BigDecimal taxes) {
        BigDecimal price = article.getPrice();
        BigDecimal multiply = price.multiply(taxes);
        BigDecimal divide = multiply.divide(new BigDecimal(100));
        return divide;
    }

    public Article getArticle() {
        return article;
    }

    public void addTaxPercentage(BigDecimal taxesPercentage) {
        this.taxesPercentage = this.taxesPercentage.add(taxesPercentage);
        updateFinalPrice();
    }

    public BigDecimal getTaxesPercentage() {
        return taxesPercentage;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    @Override
    public String getCategory() {
        return article.getCategory();
    }

    @Override
    public String getDescription() {
        return article.getDescription();
    }

    @Override
    public void setPrice(BigDecimal price) {
        finalPrice = price;
    }

    @Override
    public BigDecimal getPrice() {
        return finalPrice;
    }
}
