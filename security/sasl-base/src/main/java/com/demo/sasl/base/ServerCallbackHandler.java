package com.demo.sasl.base;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.sasl.AuthorizeCallback;
import javax.security.sasl.RealmCallback;

public class ServerCallbackHandler implements CallbackHandler{

	public void handle(final Callback[] callbacks) throws IOException,

	UnsupportedCallbackException {

		for (Callback callback : callbacks) {

			if (callback instanceof RealmCallback) {

				// do your business

			} else if (callback instanceof NameCallback) {

				// do your business

			} else if (callback instanceof PasswordCallback) {

				((PasswordCallback) callback).setPassword("admin1"

				.toCharArray());

			} else if (callback instanceof AuthorizeCallback) {

				AuthorizeCallback authCallback = ((AuthorizeCallback) callback);

				authCallback.setAuthorized(true);

			} else {

				System.out.println(callback.getClass().getName());

				throw new UnsupportedCallbackException(callback,

				"Unrecognized Callback");

			}

		}

	}
}
