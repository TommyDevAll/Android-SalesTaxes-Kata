package it.tommasoresti.salestaxes.domain.textual;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.Book;
import it.tommasoresti.salestaxes.domain.article.Food;
import it.tommasoresti.salestaxes.domain.article.Imported;
import it.tommasoresti.salestaxes.domain.article.Other;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TextualCartFactoryTest {

    private TextualCartFactory textualArticleSplitter;

    @Before
    public void setUp() throws Exception {
        textualArticleSplitter = new TextualCartFactory();
    }

    @Test
    public void given_some_products() throws Exception {
        List<Article> articles = textualArticleSplitter.make("1 book at 12.49 1 music CD at 14.99 1 chocolate bar at 0.85");
        assertThat(articles.size(), is(3));
        assertThat(articles.get(0), instanceOf(Book.class));
        assertThat(articles.get(1), instanceOf(Other.class));
        assertThat(articles.get(2), instanceOf(Food.class));
    }

    @Test
    public void given_2_local_products_and_1_imported() throws Exception {
        List<Article> articles = textualArticleSplitter.make("1 book at 12.49 1 music CD at 14.99 1 imported chocolate bar at 0.85");
        assertThat(articles.size(), is(3));
        assertThat(articles.get(0), instanceOf(Book.class));
        assertThat(articles.get(1), instanceOf(Other.class));
        assertThat(articles.get(2), instanceOf(Imported.class));

        assertThat(articles.get(2).getItem(), instanceOf(Food.class));
    }
}