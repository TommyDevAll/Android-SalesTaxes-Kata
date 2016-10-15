package it.tommasoresti.salestaxes.domain;

public class SalesTaxes {

    private TaxesRuleRepository taxesRuleRepository;

    public SalesTaxes(TaxesRuleRepository taxesRuleRepository) {
        this.taxesRuleRepository = taxesRuleRepository;
    }

    public Receipt of(Cart cart) {
        return null;
    };
}
