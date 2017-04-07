package it.tommasoresti.salestaxes.presentation.kit.concurrency;

import java.util.concurrent.Executor;

public class FutureValue<T> {

    private T value;
    private boolean interrupted = false;
    private boolean completed = false;

    private FutureCallback<T> whenReadyRunnable;
    private Executor whenReadyExecutor;

    private Runnable whenInterruptedRunnable;
    private Executor whenInterruptedExecutor;
    
    private Runnable whenErrorRunnable;
    private Executor whenErrorExecutor;

    public void whenReady(FutureCallback<T> runnable) {
        whenReady(runnable, null);
    }

    public void whenReady(FutureCallback<T> runnable, Executor executor) {
        whenReadyRunnable = runnable;
        whenReadyExecutor = executor;
        whenReadyRunnable.attach(this);
        if(completed) notifyValueReady();
    }

    public void whenInterrupted(Runnable runnable) {
        whenInterrupted(runnable, null);
    }

    public void whenInterrupted(Runnable runnable, Executor executor) {
        whenInterruptedRunnable = runnable;
        whenInterruptedExecutor = executor;
        if(interrupted) notifyInterrupted();
    }

    public void whenError(Runnable runnable) {
        whenError(runnable, null);
    }

    public void whenError(Runnable runnable, Executor executor) {
        whenErrorRunnable = runnable;
        whenErrorExecutor = executor;
    }

    public T get() {
        synchronized (lock) {
            while(!completed && !interrupted)
                try { lock.wait(); } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        return value;
    }

    public void set(T s) {
        synchronized (lock) {
            if(interrupted)
                return;
            value = s;
            completed();
        }
    }

    public void error(Error e) {
        notifyValueError();
        interrupt();
    }

    private void completed() {
        completed = true;
        notifyValueReady();
        lock.notifyAll();
    }

    public void interrupt() {
        synchronized (lock) {
            notifyInterrupted();
            interrupted = true;
            lock.notifyAll();
        }
    }

    private void notifyInterrupted() {
        if(whenInterruptedRunnable != null) {
            if(whenInterruptedExecutor != null)
                whenInterruptedExecutor.execute(whenInterruptedRunnable);
            else
                whenInterruptedRunnable.run();
        }
        whenReadyRunnable = null;
    }

    private void notifyValueReady() {
        if(whenReadyRunnable != null) {
            if(whenReadyExecutor != null)
                whenReadyExecutor.execute(whenReadyRunnable);
            else
                whenReadyRunnable.run();
        }
        whenReadyRunnable = null;
    }

    private void notifyValueError() {
        if(whenErrorRunnable != null) {
            if(whenErrorExecutor != null)
                whenErrorExecutor.execute(whenErrorRunnable);
            else
                whenErrorRunnable.run();
        }
        whenErrorRunnable = null;
    }

    private final class Lock {}
    private final Object lock = new Lock();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FutureValue<?> that = (FutureValue<?>) o;

        if (interrupted != that.interrupted) return false;
        if (completed != that.completed) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (whenReadyRunnable != null ? !whenReadyRunnable.equals(that.whenReadyRunnable) : that.whenReadyRunnable != null)
            return false;
        if (whenReadyExecutor != null ? !whenReadyExecutor.equals(that.whenReadyExecutor) : that.whenReadyExecutor != null)
            return false;
        if (whenInterruptedRunnable != null ? !whenInterruptedRunnable.equals(that.whenInterruptedRunnable) : that.whenInterruptedRunnable != null)
            return false;
        if (whenInterruptedExecutor != null ? !whenInterruptedExecutor.equals(that.whenInterruptedExecutor) : that.whenInterruptedExecutor != null)
            return false;
        if (whenErrorRunnable != null ? !whenErrorRunnable.equals(that.whenErrorRunnable) : that.whenErrorRunnable != null)
            return false;
        return whenErrorExecutor != null ? whenErrorExecutor.equals(that.whenErrorExecutor) : that.whenErrorExecutor == null;

    }
}