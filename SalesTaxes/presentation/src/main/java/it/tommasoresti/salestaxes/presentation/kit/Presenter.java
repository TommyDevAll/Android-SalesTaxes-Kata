package it.tommasoresti.salestaxes.presentation.kit;

public abstract class Presenter<V extends View> {
    private final V view;
    public Presenter(V view) {
        this.view = view;
    }

    protected V getView() { return view; }

    public abstract void onStart();
    public abstract void onResume();
    public abstract void onPause();
    public abstract void onStop();
}


