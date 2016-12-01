package it.tommasoresti.salestaxes.domain;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.TaxableArticle;
import it.tommasoresti.salestaxes.domain.tax.TaxRuleHandler;
import it.tommasoresti.salestaxes.domain.article.TaxedArticle;

public class SalesTaxes {

    private TaxRuleHandler taxesRuleHandler;
    private TaxedArticle.TaxedArticleFactory taxedArticleFactory;

    public SalesTaxes(TaxRuleHandler taxesRuleHandler, TaxedArticle.TaxedArticleFactory taxedArticleFactory) {
        this.taxesRuleHandler = taxesRuleHandler;
        this.taxedArticleFactory = taxedArticleFactory;
    }

    public Receipt of(Cart cart) {
        Receipt receipt = new Receipt();
        for(Article article : cart.getArticles()) {
            TaxableArticle taxableArticle = new TaxableArticle(article);
            if(taxesRuleHandler.canHandle(taxableArticle))
                taxesRuleHandler.handle(taxableArticle);
            receipt.addTaxedArticle(taxedArticleFactory.calculate(taxableArticle));
        }
        return receipt;
    }
}
