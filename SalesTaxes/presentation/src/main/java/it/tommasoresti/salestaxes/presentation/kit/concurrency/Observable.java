package it.tommasoresti.salestaxes.presentation.kit.concurrency;

import java.util.concurrent.Executor;

public class Observable<T> {

    private T value;
    private boolean completed = false;

    private ObservableListener<T> onChangeRunnable;
    private Executor onChangeExecutor;

    public void onChange(ObservableListener<T> listener) {
        onChange(listener, null);
    }

    public void onChange(ObservableListener<T> listener, Executor executor) {
        onChangeRunnable = listener;
        onChangeExecutor = executor;
        onChangeRunnable.attach(this);
        if(completed) notifyChange();
    }


    public T get() {
        synchronized (lock) {
            while(!completed)
                try { lock.wait(); } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        return value;
    }

    public void set(T s) {
        synchronized (lock) {
            value = s;
            completed();
        }
    }

    private void completed() {
        completed = true;
        notifyChange();
        lock.notifyAll();
    }

    private void notifyChange() {
        if(onChangeRunnable != null) {
            if(onChangeExecutor != null)
                onChangeExecutor.execute(onChangeRunnable);
            else
                onChangeRunnable.run();
        }
        onChangeRunnable = null;
    }

    private final class Lock {}
    private final Object lock = new Lock();
}