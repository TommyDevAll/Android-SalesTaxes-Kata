package it.tommasoresti.salestaxes.domain;

import it.tommasoresti.salestaxes.domain.item.Imported;
import it.tommasoresti.salestaxes.domain.item.Item;
import it.tommasoresti.salestaxes.domain.item.ItemType;

public class TaxesRule {
    public float of(Item item) {
        float taxes = 0;
        if(item instanceof Imported)
            taxes += 5f;

        if(item.getType() == ItemType.OTHER)
            taxes += 10f;
        return taxes;
    }
}
