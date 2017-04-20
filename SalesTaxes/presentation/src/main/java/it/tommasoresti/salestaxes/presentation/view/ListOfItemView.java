package it.tommasoresti.salestaxes.presentation.view;

import java.util.List;

import it.tommasoresti.salestaxes.domain.article.Item;
import it.tommasoresti.salestaxes.presentation.kit.View;

public interface ListOfItemView extends View {
    void showItems(List<Item> items);
    void showItemSelection(Item item);
}
