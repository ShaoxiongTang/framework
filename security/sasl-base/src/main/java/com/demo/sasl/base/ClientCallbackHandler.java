package com.demo.sasl.base;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.sasl.RealmCallback;

public class ClientCallbackHandler implements CallbackHandler{

	public void handle(Callback[] callbacks) throws IOException,

	UnsupportedCallbackException {

		for (int i = 0; i < callbacks.length; i++) {

			if (callbacks[i] instanceof NameCallback) {

				NameCallback ncb = (NameCallback) callbacks[i];

				ncb.setName("tony");

			} else if (callbacks[i] instanceof PasswordCallback) {

				PasswordCallback pcb = (PasswordCallback) callbacks[i];

				pcb.setPassword("admin1".toCharArray());

			} else if (callbacks[i] instanceof RealmCallback) {

				RealmCallback rcb = (RealmCallback) callbacks[i];

				rcb.setText("java.com");

			} else {

				throw new UnsupportedCallbackException(callbacks[i]);

			}

		}

	}
}
