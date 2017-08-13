
package org.jinstagram.requests;

import org.jinstagram.InstagramConstants;
import org.jinstagram.requests.payload.InstagramSearchUsersResult;

/**
 * Search Users Request
 * 
 * @author
 *
 */

public class InstagramSearchUsersRequest extends InstagramGetRequest<InstagramSearchUsersResult> {

	private String query;

	@Override
	public String getUrl() {
		return "users/search/?ig_sig_key_version=" + InstagramConstants.API_KEY_VERSION + "&is_typeahead=true&query="
				+ query + "&rank_token=" + api.getRankToken();
	}

	@Override
	public InstagramSearchUsersResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, InstagramSearchUsersResult.class);
	}
}
