
package org.jinstagram.requests.payload;

/**
 * Login Payload
 * 
 * @author
 *
 */
public class InstagramLoginPayload {
	private String username;
	private String phone_id;
	private String _csrftoken;
	private String guid;
	private String device_id;
	private String password;
	private int login_attempt_account = 0;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone_id() {
		return phone_id;
	}

	public void setPhone_id(String phone_id) {
		this.phone_id = phone_id;
	}

	public String get_csrftoken() {
		return _csrftoken;
	}

	public void set_csrftoken(String _csrftoken) {
		this._csrftoken = _csrftoken;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLogin_attempt_account() {
		return login_attempt_account;
	}

	public void setLogin_attempt_account(int login_attempt_account) {
		this.login_attempt_account = login_attempt_account;
	}

	@Override
	public String toString() {
		return "InstagramLoginPayload [username=" + username + ", phone_id=" + phone_id + ", _csrftoken=" + _csrftoken
				+ ", guid=" + guid + ", device_id=" + device_id + ", password=" + password + ", login_attempt_account="
				+ login_attempt_account + "]";
	}

	public static class Builder {
		private String username;
		private String phone_id;
		private String _csrftoken;
		private String guid;
		private String device_id;
		private String password;
		private int login_attempt_account;

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder phone_id(String phone_id) {
			this.phone_id = phone_id;
			return this;
		}

		public Builder _csrftoken(String _csrftoken) {
			this._csrftoken = _csrftoken;
			return this;
		}

		public Builder guid(String guid) {
			this.guid = guid;
			return this;
		}

		public Builder device_id(String device_id) {
			this.device_id = device_id;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder login_attempt_account(int login_attempt_account) {
			this.login_attempt_account = login_attempt_account;
			return this;
		}

		public InstagramLoginPayload build() {
			return new InstagramLoginPayload(this);
		}
	}

	private InstagramLoginPayload(Builder builder) {
		this.username = builder.username;
		this.phone_id = builder.phone_id;
		this._csrftoken = builder._csrftoken;
		this.guid = builder.guid;
		this.device_id = builder.device_id;
		this.password = builder.password;
		this.login_attempt_account = builder.login_attempt_account;
	}

	public static Builder builder() {
		return new Builder();
	}
}