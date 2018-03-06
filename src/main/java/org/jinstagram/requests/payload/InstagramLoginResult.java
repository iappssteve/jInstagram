
package org.jinstagram.requests.payload;

/**
 * Login Result
 * 
 */

public class InstagramLoginResult extends StatusResult {
	private InstagramLoggedUser logged_in_user;

	public InstagramLoggedUser getLogged_in_user() {
		return logged_in_user;
	}

	public void setLogged_in_user(InstagramLoggedUser logged_in_user) {
		this.logged_in_user = logged_in_user;
	}

	@Override
	public String toString() {
		return "InstagramLoginResult [logged_in_user=" + logged_in_user + ", toString()=" + super.toString() + "]";
	}
}