package it.tommasoresti.salestaxes.domain.textual;

import org.junit.Before;
import org.junit.Test;

import it.tommasoresti.salestaxes.domain.article.Food;
import it.tommasoresti.salestaxes.domain.article.Medical;
import it.tommasoresti.salestaxes.domain.article.Other;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class TextualArticleFactoryTest {

    private TextualArticleFactory textualArticleFactory;

    @Before
    public void setUp() throws Exception {
        textualArticleFactory = new TextualArticleFactory();
    }

    @Test
    public void given_a_chocolate_bar() throws Exception {
        String article = "1 chocolate bar at 0.85";
        assertTrue(textualArticleFactory.make(article) instanceof Food);
    }

    @Test
    public void given_a_music_cd() throws Exception {
        String article = "1 Music CD at 10.00";
        assertThat(textualArticleFactory.make(article), instanceOf(Other.class));
    }

    @Test
    public void given_some_headache_pills() throws Exception {
        String article = "1 packet of headache pills at 9.75";
        assertThat(textualArticleFactory.make(article), instanceOf(Medical.class));
    }
}