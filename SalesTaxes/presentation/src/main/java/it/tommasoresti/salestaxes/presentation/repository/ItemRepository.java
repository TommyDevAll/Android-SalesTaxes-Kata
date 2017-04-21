package it.tommasoresti.salestaxes.presentation.repository;

import java.util.List;

import it.tommasoresti.salestaxes.domain.article.Item;
import it.tommasoresti.salestaxes.presentation.kit.concurrency.Observable;

public interface ItemRepository {
    Observable<List<Item>> getItems();
}
