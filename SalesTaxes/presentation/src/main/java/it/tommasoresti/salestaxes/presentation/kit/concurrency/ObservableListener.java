package it.tommasoresti.salestaxes.presentation.kit.concurrency;

public abstract class ObservableListener<T> implements Runnable {
    private Observable<T> observable;

    protected void attach(Observable<T> observable) {
        this.observable = observable;
    }

    public abstract void callback(T data);

    @Override
    public void run() {
        callback(observable != null ? observable.get() : null);
    }
}