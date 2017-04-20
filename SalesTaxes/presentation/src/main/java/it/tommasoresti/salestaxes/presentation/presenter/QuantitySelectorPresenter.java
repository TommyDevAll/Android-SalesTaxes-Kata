package it.tommasoresti.salestaxes.presentation.presenter;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.presentation.kit.Presenter;
import it.tommasoresti.salestaxes.presentation.view.QuantitySelectorView;

public class QuantitySelectorPresenter extends Presenter<QuantitySelectorView> {
    private final Article article;
    private int quantity = 1;

    public QuantitySelectorPresenter(QuantitySelectorView view, Article article) {
        super(view);
        this.article = article;
    }

    @Override
    public void onStart() {
        updateView();
    }

    public void onIncreaseRequested() {
        quantity++;
        updateView();
    }

    public void onDecreaseRequested() {
        quantity = Math.max(quantity - 1, 1);
        updateView();
    }

    private void updateView() {
        view.showActualQuantity(quantity);
        float total = getTotal();
        view.showTotal(total);
        view.showDecreaseEnabled(quantity > 1);
    }

    private float getTotal() {
        return article.getPrice().floatValue() * quantity;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }


    @Override
    public void onStop() {

    }
}
