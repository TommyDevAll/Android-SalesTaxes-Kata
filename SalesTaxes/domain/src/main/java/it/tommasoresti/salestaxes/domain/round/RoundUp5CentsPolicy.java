package it.tommasoresti.salestaxes.domain.round;

import java.math.BigDecimal;

public class RoundUp5CentsPolicy implements RoundingPolicy {
    @Override
    public BigDecimal round(BigDecimal value) {
        BigDecimal step = new BigDecimal("0.05");
        BigDecimal division = value.divide(step, BigDecimal.ROUND_UP);
        BigDecimal integerDivision = division.setScale(0, BigDecimal.ROUND_UP);
        BigDecimal valueToNearestStep = step.multiply(integerDivision.subtract(division));
        value = value.add(valueToNearestStep);
        value = value.setScale(2, BigDecimal.ROUND_UP);
        return value;
    }
}
