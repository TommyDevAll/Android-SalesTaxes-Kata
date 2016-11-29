package it.tommasoresti.salestaxes.domain;

import it.tommasoresti.salestaxes.domain.tax.TaxRuleHandler;

public class SalesTaxes {

    private TaxRuleHandler taxesRuleHandler;

    public SalesTaxes(TaxRuleHandler taxesRuleHandler) {
        this.taxesRuleHandler = taxesRuleHandler;
    }

    public Receipt of(Cart cart) {
        return null;
    };
}
