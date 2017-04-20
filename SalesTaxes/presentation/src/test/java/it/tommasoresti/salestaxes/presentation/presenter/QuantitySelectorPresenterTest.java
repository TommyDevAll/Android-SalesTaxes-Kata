package it.tommasoresti.salestaxes.presentation.presenter;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.presentation.view.QuantitySelectorView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class QuantitySelectorPresenterTest {
    private QuantitySelectorView view;
    private QuantitySelectorPresenter presenter;
    private Article article;

    @Before
    public void setUp() throws Exception {
        view = mock(QuantitySelectorView.class);
        article = mock(Article.class);
        presenter = new QuantitySelectorPresenter(view, article);
    }

    @Test
    public void on_selector_presenter() throws Exception {
        givenAnArticleOfPrice(20);
        presenter.onStart();
        verify(view).showDecreaseEnabled(false);
        verify(view).showActualQuantity(1);
        verify(view).showTotal(20);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void on_quantity_increased() throws Exception {
        givenAnArticleOfPrice(20);
        presenter.onIncreaseRequested();
        verify(view).showDecreaseEnabled(true);
        verify(view).showActualQuantity(2);
        verify(view).showTotal(40);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void on_quantity_decreased() throws Exception {
        givenAnArticleOfPrice(20);
        presenter.onDecreaseRequested();
        verify(view).showDecreaseEnabled(false);
        verify(view).showActualQuantity(1);
        verify(view).showTotal(20);
        verifyNoMoreInteractions(view);
    }

    private void givenAnArticleOfPrice(float val) {
        when(article.getPrice()).thenReturn(new BigDecimal(val));
    }
}