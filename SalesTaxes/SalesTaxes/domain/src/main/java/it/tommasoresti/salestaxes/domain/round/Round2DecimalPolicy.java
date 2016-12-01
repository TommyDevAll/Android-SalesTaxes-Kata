package it.tommasoresti.salestaxes.domain.round;

import java.math.BigDecimal;

public class Round2DecimalPolicy implements RoundingPolicy {
    public BigDecimal round(BigDecimal value) {
        return Round2DecimalPolicy.twoDecimal(value);
    }

    public static BigDecimal twoDecimal(BigDecimal value) {
        return value.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}