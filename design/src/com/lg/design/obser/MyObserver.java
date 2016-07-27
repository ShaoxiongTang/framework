package com.lg.design.obser;

import java.util.Observable;

public interface MyObserver<T extends Observable>{

	public void update(T t,Object args);
}
