package com.lg.design.obser.impl;

import java.util.Collection;

public interface NotifyOrder{

	public Observer<CollectionObservable>[] notifyObserver(Collection<Observer<? extends CollectionObservable>> obs);
}
