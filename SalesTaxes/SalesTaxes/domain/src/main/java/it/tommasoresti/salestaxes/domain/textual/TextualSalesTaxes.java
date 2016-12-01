package it.tommasoresti.salestaxes.domain.textual;

import java.util.List;

import it.tommasoresti.salestaxes.domain.Cart;
import it.tommasoresti.salestaxes.domain.Receipt;
import it.tommasoresti.salestaxes.domain.SalesTaxes;
import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.round.Round2DecimalPolicy;

class TextualSalesTaxes {
    private SalesTaxes salesTaxes;
    private final TextualCartFactory splitter;

    public TextualSalesTaxes(SalesTaxes salesTaxes) {
        this.salesTaxes = salesTaxes;
        splitter = new TextualCartFactory(new TextualArticleFactory());
    }

    public String of(String cartString) {
        Cart cart = buildCart(cartString);
        Receipt receipt = salesTaxes.of(cart);
        return new TextualReceipt(receipt, new Round2DecimalPolicy()).toString();
    }

    private Cart buildCart(String cartString) {
        Cart cart = new Cart();
        List<Article> articleList = splitter.make(cartString);
        for(Article article : articleList)
            cart.addArticle(article);
        return cart;
    }
}
