package it.tommasoresti.salestaxes.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import it.tommasoresti.salestaxes.domain.item.Book;
import it.tommasoresti.salestaxes.domain.item.ChocolateBar;
import it.tommasoresti.salestaxes.domain.item.ChocolateBox;
import it.tommasoresti.salestaxes.domain.item.Imported;
import it.tommasoresti.salestaxes.domain.item.Item;
import it.tommasoresti.salestaxes.domain.item.MusicCD;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CartTest {
    private Cart cart;

    @Before
    public void setUp() throws Exception {
        cart = new Cart();
    }

    @Test
    public void given_a_chocolate_bar() throws Exception {
        ChocolateBar chocolateBar = new ChocolateBar();
        cart.addItem(chocolateBar);
        assertThat(cart.getItems(), equalTo(Collections.<Item>singletonList(chocolateBar)));
    }

    @Test
    public void given_a_box_of_chocolate() throws Exception {
        ChocolateBox chocolateBox = new ChocolateBox();
        cart.addItem(chocolateBox);
        assertThat(cart.getItems(), equalTo(Collections.<Item>singletonList(chocolateBox)));
    }

    @Test
    public void given_a_book() throws Exception {
        Book book = new Book();
        cart.addItem(book);
        assertThat(cart.getItems(), equalTo(Collections.<Item>singletonList(book)));
    }

    @Test
    public void given_a_music_cd() throws Exception {
        MusicCD musicCD = new MusicCD();
        cart.addItem(musicCD);
        assertThat(cart.getItems(), equalTo(Collections.<Item>singletonList(musicCD)));
    }

    @Test
    public void given_an_imported_chocolate_bar() throws Exception {
        Imported importedChocolate = new Imported(new ChocolateBar());
        cart.addItem(importedChocolate);
        assertThat(cart.getItems(), equalTo(Collections.<Item>singletonList(importedChocolate)));
    }
}