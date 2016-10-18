package it.tommasoresti.salestaxes.domain.textual;

import org.junit.Before;
import org.junit.Test;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.Book;
import it.tommasoresti.salestaxes.domain.article.Food;
import it.tommasoresti.salestaxes.domain.article.Imported;
import it.tommasoresti.salestaxes.domain.article.Medical;
import it.tommasoresti.salestaxes.domain.article.Other;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TextualArticleFactoryTest {

    private TextualArticleFactory textualArticleFactory;

    @Before
    public void setUp() throws Exception {
        textualArticleFactory = new TextualArticleFactory();
    }

    @Test
    public void given_a_chocolate_bar() throws Exception {
        Article article = textualArticleFactory.make("chocolate", 0.85f);
        assertThat(article, instanceOf(Food.class));
        assertThat(article.getPrice(), is(0.85f));
    }

    @Test
    public void given_some_headache_pills() throws Exception {
        assertThat(textualArticleFactory.make("packet of headache pills", 9.75f), instanceOf(Medical.class));
    }

    @Test
    public void given_a_book() throws Exception {
        assertThat(textualArticleFactory.make("harry potter book", 9.75f), instanceOf(Book.class));
    }

    @Test
    public void given_a_music_cd() throws Exception {
        assertThat(textualArticleFactory.make("Music CD", 10.00f), instanceOf(Other.class));
    }

    @Test
    public void given_an_imported_chocolate_bar() throws Exception {
        assertThat(textualArticleFactory.make("imported chocolate bar", 10.00f), instanceOf(Imported.class));
    }
}