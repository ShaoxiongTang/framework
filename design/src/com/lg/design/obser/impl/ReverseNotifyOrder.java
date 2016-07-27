package com.lg.design.obser.impl;

import java.util.Collection;

public class ReverseNotifyOrder implements NotifyOrder{

	@SuppressWarnings({"unchecked" })
	@Override
	public Observer<CollectionObservable>[] notifyObserver(Collection<Observer<? extends CollectionObservable>> obs) {
		Object[] ret=obs.toArray();
		Observer<CollectionObservable>[] observers=new Observer[obs.size()];
		for(int i=0,len=ret.length;i<len;i++){
			observers[len-i-1]=(Observer<CollectionObservable>) ret[i];
		}
		return observers; 
	}
}
