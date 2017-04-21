package it.tommasoresti.salestaxes.presentation.kit.concurrency;

import java.util.concurrent.Executor;

public class Observable<T> {

    private T value;
    private boolean completed = false;

    private ObservableListener<T> onSetRunnable;
    private Executor onSetExecutor;

    public void onSet(ObservableListener<T> listener) {
        onSet(listener, null);
    }

    public void onSet(ObservableListener<T> listener, Executor executor) {
        onSetRunnable = listener;
        onSetExecutor = executor;
        onSetRunnable.attach(this);
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
        if(onSetRunnable != null) {
            if(onSetExecutor != null)
                onSetExecutor.execute(onSetRunnable);
            else
                onSetRunnable.run();
        }
        onSetRunnable = null;
    }

    private final class Lock {}
    private final Object lock = new Lock();
}