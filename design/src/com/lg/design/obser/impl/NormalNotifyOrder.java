package com.lg.design.obser.impl;

import java.util.Collection;

public class NormalNotifyOrder implements NotifyOrder{

	@SuppressWarnings("unchecked")
	@Override
	public Observer<CollectionObservable>[] notifyObserver(Collection<Observer<? extends CollectionObservable>> obs) {
		Object[] ret=obs.toArray(new Observer[3]);
		return (Observer<CollectionObservable>[]) ret;
	}

}
