
package org.jinstagram.requests.payload;

/**
 * Data class for response from check username
 *
 */
public class InstagramCheckUsernameResult extends StatusResult {
	private boolean available;
	private String username;
	private String error;
	private String error_type;

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError_type() {
		return error_type;
	}

	public void setError_type(String error_type) {
		this.error_type = error_type;
	}
}
