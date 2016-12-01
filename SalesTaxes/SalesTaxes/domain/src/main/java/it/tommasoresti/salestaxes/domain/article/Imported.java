package it.tommasoresti.salestaxes.domain.article;

public class Imported extends ArticleDecorator {

    public Imported(Article article) {
        super(article);
    }

    @Override
    public String getDescription() {
        return "imported " + super.getDescription();
    }
}
