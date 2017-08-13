
package org.jinstagram.requests.payload;

/**
 * Search Username Result
 *
 */
public class InstagramSearchUsernameResult extends StatusResult {
	private InstagramUser user;

	public InstagramUser getUser() {
		return user;
	}

	public void setUser(InstagramUser user) {
		this.user = user;
	}
}