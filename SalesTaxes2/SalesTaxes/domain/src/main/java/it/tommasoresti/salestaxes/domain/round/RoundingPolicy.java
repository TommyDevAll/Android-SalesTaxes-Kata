package it.tommasoresti.salestaxes.domain.round;

import java.math.BigDecimal;

public interface RoundingPolicy {
    BigDecimal round(BigDecimal value);
}
