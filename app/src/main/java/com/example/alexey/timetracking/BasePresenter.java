package com.example.alexey.timetracking;

public abstract class BasePresenter<V> {
    private V view;

    public void onAttach(V view) {
        this.view = view;
    }

    public void onDetach() {
        this.view = null;
    }

    protected V getView() {
        return this.view;
    }

    protected boolean isViewAttached() {
        return view != null;
    }
}
