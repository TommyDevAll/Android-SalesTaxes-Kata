package it.tommasoresti.salestaxes.domain.item;

public class Food extends Item {
    @Override
    public ItemType getType() {
        return ItemType.FOOD;
    }
}
