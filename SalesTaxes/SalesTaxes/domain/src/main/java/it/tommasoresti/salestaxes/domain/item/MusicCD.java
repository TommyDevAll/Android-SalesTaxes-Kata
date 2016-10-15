package it.tommasoresti.salestaxes.domain.item;

public class MusicCD extends Item {
    @Override
    public ItemType getType() {
        return ItemType.OTHER;
    }
}
