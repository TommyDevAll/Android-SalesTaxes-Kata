package it.tommasoresti.salestaxes.presentation.kit.concurrency;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ObservableTest {

    private FakeObservableListener listener;

    @Before
    public void setUp() throws Exception {
        listener = new FakeObservableListener();
    }

    @Test
    public void on_set_notified() throws Exception {
        Observable<String> stringObservable = new Observable<>();
        stringObservable.onSet(listener);
        stringObservable.set("::test::");

        assertTrue(listener.called);
        assertEquals(listener.value, "::test::");
    }

    private class FakeObservableListener extends ObservableListener<String> {
        public boolean called = false;
        public String value;

        @Override
        public void callback(String data) {
            called = true;
            value = data;
        }
    }
}