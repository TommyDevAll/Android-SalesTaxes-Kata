package it.tommasoresti.salestaxes.domain.textual;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import it.tommasoresti.salestaxes.domain.ArticleFactory;
import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.Other;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TextualCartFactoryTest {

    private TextualCartFactory textualArticleSplitter;
    private ArticleFactory articleFactory;

    @Before
    public void setUp() throws Exception {
        articleFactory = mock(ArticleFactory.class);
        textualArticleSplitter = new TextualCartFactory(articleFactory);

        when(articleFactory.make(anyString(), anyFloat())).thenReturn(new Other(""));
    }

    @Test
    public void given_some_products() throws Exception {
        List<Article> articles = textualArticleSplitter.make("1 object at 12.49 1 magic object at 14.99 1 other incredible object at 0.85");
        assertThat(articles.size(), is(3));
    }
}