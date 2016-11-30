package it.tommasoresti.salestaxes.domain.textual;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import it.tommasoresti.salestaxes.domain.article.Article;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TextualArticleTest {
    private TextualCartFactory factory;

    @Before
    public void setUp() throws Exception {
        this.factory = new TextualCartFactory(new TextualArticleFactory());
    }

    @Test
    public void given_first_use_case_input() throws Exception {
        List<Article> articles = factory.make("1 book at 12.49 1 music CD at 14.99 1 chocolate bar at 0.85");

        assertThat(articles.size(), is(3));

        assertThat(new TextualArticle(articles.get(0)).toString(), is(equalTo("1 book: 12.49")));
        assertThat(new TextualArticle(articles.get(1)).toString(), is(equalTo("1 music CD: 14.99")));
        assertThat(new TextualArticle(articles.get(2)).toString(), is(equalTo("1 chocolate bar: 0.85")));
    }

    @Test
    public void given_second_use_case_input() throws Exception {
        List<Article> articles = factory.make("1 imported box of chocolates at 10.00 1 imported bottle of perfume at 47.50");

        assertThat(articles.size(), is(2));

        assertThat(new TextualArticle(articles.get(0)).toString(), is(equalTo("1 imported box of chocolates: 10.00")));
        assertThat(new TextualArticle(articles.get(1)).toString(), is(equalTo("1 imported bottle of perfume: 47.50")));
    }

    @Test
    public void given_third_use_case_input() throws Exception {
        List<Article> articles = factory.make("1 imported bottle of perfume at 27.99 1 bottle of perfume at 18.99 1 packet of headache pills at 9.75 1 box of imported chocolates at 11.25");

        assertThat(articles.size(), is(4));

        assertThat(new TextualArticle(articles.get(0)).toString(), is(equalTo("1 imported bottle of perfume: 27.99")));
        assertThat(new TextualArticle(articles.get(1)).toString(), is(equalTo("1 bottle of perfume: 18.99")));
        assertThat(new TextualArticle(articles.get(2)).toString(), is(equalTo("1 packet of headache pills: 9.75")));
        assertThat(new TextualArticle(articles.get(3)).toString(), is(equalTo("1 imported box of chocolates: 11.25")));
    }

}
