
package org.jinstagram.requests;

import org.jinstagram.InstagramConstants;
import org.jinstagram.requests.payload.InstagramGetUserFollowersResult;

/**
 * Get User Followers Request
 * 
 */
public class InstagramGetUserFollowingRequest extends InstagramGetRequest<InstagramGetUserFollowersResult> {
	private long userId;
	private String maxId;

	@Override
	public String getUrl() {
		String baseUrl = "friendships/" + userId + "/following/?rank_token=" + api.getRankToken()
				+ "&ig_sig_key_version=" + InstagramConstants.API_KEY_VERSION;
		if (maxId != null && !maxId.isEmpty()) {
			baseUrl += "&max_id=" + maxId;
		}
		return baseUrl;
	}

	@Override
	public InstagramGetUserFollowersResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, InstagramGetUserFollowersResult.class);
	}
}
