package org.jinstagram;

public class JInstagramException extends Exception {
	private static final long serialVersionUID = -3310942014011956832L;

	public JInstagramException() {
	}

	public JInstagramException(String message) {
		super(message);
	}

	public JInstagramException(Throwable cause) {
		super(cause);
	}

	public JInstagramException(String message, Throwable cause) {
		super(message, cause);
	}

	public JInstagramException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
