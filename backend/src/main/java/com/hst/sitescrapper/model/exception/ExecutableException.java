package com.hst.sitescrapper.model.exception;

/**
 * @author dlgusrb0808@gmail.com
 */
public class ExecutableException extends RuntimeException {
	private Exception cause;
	public ExecutableException(Exception cause) {
		this.cause = cause;
	}

	@Override
	public Exception getCause() {
		return cause;
	}
}
