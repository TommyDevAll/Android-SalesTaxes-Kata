package it.tommasoresti.salestaxes.domain;

public class SalesTaxes {

    private TaxesRuleChain taxesRuleRepository;

    public SalesTaxes(TaxesRuleChain taxesRuleRepository) {
        this.taxesRuleRepository = taxesRuleRepository;
    }

    public Receipt of(Cart cart) {
        return null;
    };
}
