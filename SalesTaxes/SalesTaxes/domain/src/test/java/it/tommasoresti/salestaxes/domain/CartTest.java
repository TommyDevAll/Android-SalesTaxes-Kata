package it.tommasoresti.salestaxes.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.Imported;
import it.tommasoresti.salestaxes.domain.article.Item;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CartTest {
    private Cart cart;
    private BigDecimal aPrice;

    @Before
    public void setUp() throws Exception {
        cart = new Cart();
        aPrice = new BigDecimal(10);
    }

    @Test
    public void given_a_chocolate_bar() throws Exception {
        Item chocolateBar = new Item("food", "chocolate bar", aPrice);
        cart.addArticle(chocolateBar);
        assertThat(cart.getArticles(), equalTo(Collections.<Article>singletonList(chocolateBar)));
    }

    @Test
    public void given_a_box_of_chocolate() throws Exception {
        Item chocolateBox = new Item("food", "chocolate box", aPrice);
        cart.addArticle(chocolateBox);
        assertThat(cart.getArticles(), equalTo(Collections.<Article>singletonList(chocolateBox)));
    }

    @Test
    public void given_a_book() throws Exception {
        Item book = new Item("book", "harry potter", aPrice);
        cart.addArticle(book);
        assertThat(cart.getArticles(), equalTo(Collections.<Article>singletonList(book)));
    }

    @Test
    public void given_a_music_cd() throws Exception {
        Item musicCD = new Item("other", "music CD", aPrice);
        cart.addArticle(musicCD);
        assertThat(cart.getArticles(), equalTo(Collections.<Article>singletonList(musicCD)));
    }

    @Test
    public void given_an_imported_chocolate_bar() throws Exception {
        Imported importedChocolate = new Imported(new Item("food", "chocolate bar", aPrice));
        cart.addArticle(importedChocolate);
        assertThat(cart.getArticles(), equalTo(Collections.<Article>singletonList(importedChocolate)));
    }
}