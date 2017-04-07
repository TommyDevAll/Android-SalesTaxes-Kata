package it.tommasoresti.salestaxes.domain.round;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RoundUp5CentsPolicyTest {
    private RoundUp5CentsPolicy roundingPolicy;

    @Before
    public void setUp() throws Exception {
        this.roundingPolicy = new RoundUp5CentsPolicy();
    }

    @Test
    public void given_some_values() throws Exception {
        assertThat(roundingPolicy.round(new BigDecimal("10")), is(new BigDecimal("10.00")));
        assertThat(roundingPolicy.round(new BigDecimal("10.02")), is(new BigDecimal("10.05")));
        assertThat(roundingPolicy.round(new BigDecimal("0.5")), is(new BigDecimal("0.50")));
        assertThat(roundingPolicy.round(new BigDecimal("0.00000000000001")), is(new BigDecimal("0.05")));
    }
}