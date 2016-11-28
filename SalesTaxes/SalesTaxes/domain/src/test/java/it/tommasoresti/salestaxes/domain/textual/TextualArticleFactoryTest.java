package it.tommasoresti.salestaxes.domain.textual;

import org.junit.Before;
import org.junit.Test;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.Imported;
import it.tommasoresti.salestaxes.domain.article.Item;

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
        assertThat(article.getItem().getCategory(), is("food"));
        assertThat(article.getPrice(), is(0.85f));
    }

    @Test
    public void given_some_headache_pills() throws Exception {
        Item item = textualArticleFactory.make("packet of headache pills", 9.75f).getItem();
        assertThat(item.getCategory(), is("medical"));
    }

    @Test
    public void given_a_book() throws Exception {
        Item item = textualArticleFactory.make("harry potter book", 9.75f).getItem();
        assertThat(item.getCategory(), is("book"));
    }

    @Test
    public void given_a_music_cd() throws Exception {
        Item item = textualArticleFactory.make("Music CD", 10.00f).getItem();
        assertThat(item.getCategory(), is("other"));
    }

    @Test
    public void given_an_imported_chocolate_bar() throws Exception {
        assertThat(textualArticleFactory.make("imported chocolate bar", 10.00f), instanceOf(Imported.class));
    }
}