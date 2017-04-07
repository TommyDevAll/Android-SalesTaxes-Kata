package it.tommasoresti.salestaxes.presentation.kit.concurrency;

public abstract class FutureCallback<T> implements Runnable {
    private FutureValue<T> futureValue;

    protected void attach(FutureValue<T> futureValue) {
        this.futureValue = futureValue;
    }

    public abstract void callback(T data);

    @Override
    public void run() {
        callback(futureValue != null ? futureValue.get() : null);
    }
}