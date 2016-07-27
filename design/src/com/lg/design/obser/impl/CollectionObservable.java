package com.lg.design.obser.impl;

import java.util.Collection;
import java.util.Vector;

/**
 * add a changed status
 * add a Collection<Observer>
 * @author lg
 *
 */
public class CollectionObservable{
	
	private Collection<Observer<? extends CollectionObservable>> collection=new Vector<Observer<? extends CollectionObservable>>();
	private NotifyOrder notifyOrder=new ReverseNotifyOrder();
	
	public void notifyObservers() {
		notifyObservers(null);
	}

	public synchronized void addObserver(Observer<? extends CollectionObservable> observer) {
		collection.add(observer);
	}

	public synchronized void deleteObserver(Observer<? extends CollectionObservable> observer) {
		collection.remove(observer);
	}

	@SuppressWarnings("unchecked")
	public synchronized void notifyObservers(Object arg) {
        @SuppressWarnings("rawtypes")
		Observer[] ret=notifyOrder.notifyObserver(collection);
        for (int i = 0,len=ret.length; i<len; i++)
            (ret[i]).update(this, arg);
	}

	public int countObservers() {
		return collection.size();
	}

	public Collection<Observer<? extends CollectionObservable>> getCollection() {
		return collection;
	}

	public void setCollection(Collection<Observer<? extends CollectionObservable>> collection) {
		this.collection = collection;
	}

	public NotifyOrder getNotifyOrder() {
		return notifyOrder;
	}

	public void setNotifyOrder(NotifyOrder notifyOrder) {
		this.notifyOrder = notifyOrder;
	}
	
}
