package it.tommasoresti.salestaxes.domain.round;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class Round2DecimalPolicyTest {
    @Test
    public void simple_test() throws Exception {
        Round2DecimalPolicy policy = new Round2DecimalPolicy();

        assertThat(policy.round(new BigDecimal(2)), is(new BigDecimal("2.00")));
        assertThat(policy.round(new BigDecimal("2.001")), is(new BigDecimal("2.00")));
        assertThat(policy.round(new BigDecimal("-2.001")), is(new BigDecimal("-2.00")));
    }
}