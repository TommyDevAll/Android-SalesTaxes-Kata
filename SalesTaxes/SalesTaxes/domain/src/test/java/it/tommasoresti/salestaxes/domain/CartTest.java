package it.tommasoresti.salestaxes.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.Book;
import it.tommasoresti.salestaxes.domain.article.Food;
import it.tommasoresti.salestaxes.domain.article.Imported;
import it.tommasoresti.salestaxes.domain.article.Other;

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
        Food chocolateBar = new Food("chocolate bar", 1);
        cart.addArticle(chocolateBar);
        assertThat(cart.getArticles(), equalTo(Collections.<Article>singletonList(chocolateBar)));
    }

    @Test
    public void given_a_box_of_chocolate() throws Exception {
        Food chocolateBox = new Food("chocolate box", 1);
        cart.addArticle(chocolateBox);
        assertThat(cart.getArticles(), equalTo(Collections.<Article>singletonList(chocolateBox)));
    }

    @Test
    public void given_a_book() throws Exception {
        Book book = new Book("harry potter", 1);
        cart.addArticle(book);
        assertThat(cart.getArticles(), equalTo(Collections.<Article>singletonList(book)));
    }

    @Test
    public void given_a_music_cd() throws Exception {
        Other musicCD = new Other("music CD", 1);
        cart.addArticle(musicCD);
        assertThat(cart.getArticles(), equalTo(Collections.<Article>singletonList(musicCD)));
    }

    @Test
    public void given_an_imported_chocolate_bar() throws Exception {
        Imported importedChocolate = new Imported(new Food("chocolate bar", 1));
        cart.addArticle(importedChocolate);
        assertThat(cart.getArticles(), equalTo(Collections.<Article>singletonList(importedChocolate)));
    }
}