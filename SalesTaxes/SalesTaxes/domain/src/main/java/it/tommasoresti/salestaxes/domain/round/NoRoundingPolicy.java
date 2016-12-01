package it.tommasoresti.salestaxes.domain.round;

import java.math.BigDecimal;

public class NoRoundingPolicy implements RoundingPolicy {
    public BigDecimal round(BigDecimal value) {
        return value;
    }
}
