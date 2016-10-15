package it.tommasoresti.salestaxes.domain.item;

public class Other extends Item {
    public Other(String description) {
        super(description);
    }

    @Override
    public ItemType getType() {
        return ItemType.OTHER;
    }
}
