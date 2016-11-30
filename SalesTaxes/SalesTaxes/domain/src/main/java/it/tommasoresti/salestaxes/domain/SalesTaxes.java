package it.tommasoresti.salestaxes.domain;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.TaxableArticle;
import it.tommasoresti.salestaxes.domain.tax.TaxRuleHandler;

public class SalesTaxes {

    private TaxRuleHandler taxesRuleHandler;

    public SalesTaxes(TaxRuleHandler taxesRuleHandler) {
        this.taxesRuleHandler = taxesRuleHandler;
    }

    public Receipt of(Cart cart) {
        Receipt receipt = new Receipt();
        for(Article article : cart.getArticles()) {
            TaxableArticle taxedArticle = new TaxableArticle(article);
            if(taxesRuleHandler.canHandle(taxedArticle))
                taxesRuleHandler.handle(taxedArticle);
            receipt.addTaxedArticle(taxedArticle);
        }
        return receipt;
    }
}
