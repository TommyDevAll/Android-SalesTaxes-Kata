package it.tommasoresti.salestaxes.presentation.presenter;

import java.util.List;

import it.tommasoresti.salestaxes.domain.article.Item;
import it.tommasoresti.salestaxes.presentation.kit.Presenter;
import it.tommasoresti.salestaxes.presentation.kit.concurrency.FutureCallback;
import it.tommasoresti.salestaxes.presentation.repository.ItemRepository;
import it.tommasoresti.salestaxes.presentation.view.ListOfItemView;

public class ListOfItemPresenter extends Presenter<ListOfItemView> {

    private final ItemRepository repository;

    public ListOfItemPresenter(ListOfItemView view, ItemRepository repository) {
        super(view);
        this.repository = repository;
    }

    @Override
    public void onStart() {
        repository.getItems().whenReady(new FutureCallback<List<Item>>() {
            @Override
            public void callback(List<Item> items) {
                view.showItems(items);
            }
        });
    }

    public void onItemSelected(Item item) {
        view.showItemSelection(item);
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
