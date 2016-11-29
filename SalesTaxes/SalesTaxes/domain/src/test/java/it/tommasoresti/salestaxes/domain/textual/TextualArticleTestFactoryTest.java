package it.tommasoresti.salestaxes.domain.textual;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.Imported;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TextualArticleTestFactoryTest {

    private TextualArticleFactory textualArticleFactory;

    @Before
    public void setUp() throws Exception {
        textualArticleFactory = new TextualArticleFactory();
    }

    @Test
    public void given_a_chocolate_bar() throws Exception {
        Article article = textualArticleFactory.make("chocolate", new BigDecimal(0.85f));
        assertThat(article.getCategory(), is("food"));
        assertThat(article.getPrice(), is(equalTo(new BigDecimal(0.85f))));
    }

    @Test
    public void given_some_headache_pills() throws Exception {
        Article article = textualArticleFactory.make("packet of headache pills", new BigDecimal(9.75f));
        assertThat(article.getCategory(), is("medical"));
    }

    @Test
    public void given_a_book() throws Exception {
        Article article = textualArticleFactory.make("harry potter book", new BigDecimal(9.75f));
        assertThat(article.getCategory(), is("book"));
    }

    @Test
    public void given_a_music_cd() throws Exception {
        Article article = textualArticleFactory.make("Music CD", new BigDecimal(10.00f));
        assertThat(article.getCategory(), is("other"));
    }

    @Test
    public void given_an_imported_chocolate_bar() throws Exception {
        assertThat(textualArticleFactory.make("imported chocolate bar", new BigDecimal(10.00f)), instanceOf(Imported.class));
    }
}