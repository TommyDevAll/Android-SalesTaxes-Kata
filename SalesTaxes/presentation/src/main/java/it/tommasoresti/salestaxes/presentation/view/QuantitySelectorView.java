package it.tommasoresti.salestaxes.presentation.view;

import it.tommasoresti.salestaxes.presentation.kit.View;

public interface QuantitySelectorView extends View {
    void showActualQuantity(int i);
    void showTotal(float total);
    void showDecreaseEnabled(boolean enabled);
}
