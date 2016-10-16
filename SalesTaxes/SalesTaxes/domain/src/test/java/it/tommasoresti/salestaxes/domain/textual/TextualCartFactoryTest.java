package it.tommasoresti.salestaxes.domain.textual;

import org.junit.Before;
import org.junit.Test;

public class TextualCartFactoryTest {

    private TextualCartFactory textualArticleSplitter;

    @Before
    public void setUp() throws Exception {
        textualArticleSplitter = new TextualCartFactory();
    }

    @Test
    public void given_some_products() throws Exception {
        textualArticleSplitter.make("1 book at 12.49 1 music CD at 14.99 1 chocolate bar at 0.85");
    }
}