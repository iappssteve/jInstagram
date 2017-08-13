
package org.jinstagram.requests;

import org.jinstagram.requests.payload.InstagramSearchUsernameResult;

/**
 * Search Username Request
 * 
 */
public class InstagramSearchUsernameRequest extends InstagramGetRequest<InstagramSearchUsernameResult> {
	private String username;

	@Override
	public String getUrl() {
		return "users/" + username + "/usernameinfo/";
	}

	@Override
	public InstagramSearchUsernameResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, InstagramSearchUsernameResult.class);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
