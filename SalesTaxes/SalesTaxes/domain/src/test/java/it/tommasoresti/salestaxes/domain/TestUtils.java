package it.tommasoresti.salestaxes.domain;

import java.math.BigDecimal;

public class TestUtils {
    public static BigDecimal round2Decimals(BigDecimal value) {
        return value.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
