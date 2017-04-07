package it.tommasoresti.salestaxes.domain;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.TaxableArticle;
import it.tommasoresti.salestaxes.domain.round.RoundingPolicy;
import it.tommasoresti.salestaxes.domain.tax.TaxRuleHandler;
import it.tommasoresti.salestaxes.domain.article.TaxedArticle;

public class SalesTaxes {

    private TaxRuleHandler taxesRuleHandler;
    private TaxedArticle.Factory factory;

    public SalesTaxes(TaxRuleHandler taxesRuleHandler, RoundingPolicy roundingPolicy) {
        this.taxesRuleHandler = taxesRuleHandler;
        this.factory = new TaxedArticle.Factory(roundingPolicy);
    }

    public Receipt of(Cart cart) {
        Receipt receipt = new Receipt();
        for(Article article : cart.getArticles()) {
            TaxableArticle taxableArticle = new TaxableArticle(article);
            if(taxesRuleHandler.canHandle(taxableArticle))
                taxableArticle = taxesRuleHandler.handle(taxableArticle);
            receipt.addTaxedArticle(factory.make(taxableArticle));
        }
        return receipt;
    }
}
