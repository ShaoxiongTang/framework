package com.lg.design.obser.impl;

public interface Observer<T extends CollectionObservable>{
	
	void update(T t, Object arg);
	
}
