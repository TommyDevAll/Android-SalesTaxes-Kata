package it.tommasoresti.salestaxes.domain;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.TaxableArticle;
import it.tommasoresti.salestaxes.domain.tax.TaxCalculator;
import it.tommasoresti.salestaxes.domain.tax.TaxRuleHandler;

public class SalesTaxes {

    private TaxRuleHandler taxesRuleHandler;
    private TaxCalculator taxCalculator;

    public SalesTaxes(TaxRuleHandler taxesRuleHandler, TaxCalculator taxCalculator) {
        this.taxesRuleHandler = taxesRuleHandler;
        this.taxCalculator = taxCalculator;
    }

    public Receipt of(Cart cart) {
        Receipt receipt = new Receipt();
        for(Article article : cart.getArticles()) {
            TaxableArticle taxableArticle = new TaxableArticle(article);
            if(taxesRuleHandler.canHandle(taxableArticle))
                taxesRuleHandler.handle(taxableArticle);
            receipt.addTaxedArticle(taxCalculator.calculate(taxableArticle));
        }
        return receipt;
    }
}
