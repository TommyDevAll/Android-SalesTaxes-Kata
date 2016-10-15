package it.tommasoresti.salestaxes.domain.item;

public class Medical extends Item {
    public Medical(String description) {
        super(description);
    }

    @Override
    public ItemType getType() {
        return ItemType.MEDICAL;
    }
}
