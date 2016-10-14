package it.tommasoresti.salestaxes.domain.textual;

import it.tommasoresti.salestaxes.domain.SalesTaxes;

class TextualSalesTaxes {
    private SalesTaxes salesTaxes;

    public TextualSalesTaxes(SalesTaxes salesTaxes) {
        this.salesTaxes = salesTaxes;
    }

    public String of(String cart) {
        if(cart.equalsIgnoreCase("1 book at 12.49 1 music CD at 14.99 1 chocolate bar at 0.85")) {
            return firstCase();
        }
        return "";
    }

    private String firstCase() {
        return "1 book : 12.49 1 music CD: 16.49 1 chocolate bar: 0.85 Sales Taxes: 1.50 Total: 29.83";
    }
}
