package it.tommasoresti.salestaxes.domain.article;

public class Imported extends ArticleWrapper {

    public Imported(Article article) {
        super(article);
    }

    @Override
    public String getDescription() {
        return "imported " + super.getDescription();
    }
}
