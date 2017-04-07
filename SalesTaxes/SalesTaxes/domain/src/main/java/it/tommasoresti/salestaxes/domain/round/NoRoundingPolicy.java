package it.tommasoresti.salestaxes.domain.round;

import java.math.BigDecimal;

public class NoRoundingPolicy implements RoundingPolicy {
    @Override
    public BigDecimal round(BigDecimal value) {
        return value;
    }
}
