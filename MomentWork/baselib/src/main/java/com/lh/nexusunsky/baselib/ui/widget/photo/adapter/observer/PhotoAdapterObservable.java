package com.lh.nexusunsky.baselib.ui.widget.photo.adapter.observer;

/**
 * @author Nexusunsky
 */
public class PhotoAdapterObservable extends PhotoImageObservable<PhotoBaseDataObserver> {

    public void notifyChanged() {
        synchronized (mObservers) {
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onChanged();
            }
        }
    }

    public void notifyInvalidated() {
        synchronized (mObservers) {
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onInvalidated();
            }
        }
    }
}
