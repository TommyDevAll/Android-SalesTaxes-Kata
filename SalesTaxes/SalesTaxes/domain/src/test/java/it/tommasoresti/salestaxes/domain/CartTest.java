package it.tommasoresti.salestaxes.domain;

import org.junit.Test;

import java.util.Collections;

import it.tommasoresti.salestaxes.domain.item.ChocolateBar;
import it.tommasoresti.salestaxes.domain.item.Item;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CartTest {
    @Test
    public void given_a_chocolate_bar() throws Exception {
        Cart cart = new Cart();
        ChocolateBar chocolateBar = new ChocolateBar();
        cart.addItem(chocolateBar);
        assertThat(cart.getItems(), equalTo(Collections.<Item>singletonList(chocolateBar)));
    }
}