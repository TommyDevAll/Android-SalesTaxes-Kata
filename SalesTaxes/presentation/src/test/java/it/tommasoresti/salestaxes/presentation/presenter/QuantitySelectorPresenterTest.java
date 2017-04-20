package it.tommasoresti.salestaxes.presentation.presenter;

import org.junit.Before;
import org.junit.Test;

import it.tommasoresti.salestaxes.presentation.view.QuantitySelectorView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class QuantitySelectorPresenterTest {
    private QuantitySelectorView view;
    private QuantitySelectorPresenter presenter;

    @Before
    public void setUp() throws Exception {

        view = mock(QuantitySelectorView.class);
        presenter = new QuantitySelectorPresenter(view);
    }

    @Test
    public void on_selector_presenter() throws Exception {
        presenter.onStart();
        verify(view).showActualQuantity(1);
        verify(view).showTotal(10);
    }
}