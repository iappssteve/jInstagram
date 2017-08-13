
package org.jinstagram.requests;

import org.jinstagram.requests.payload.InstagramFeedResult;

/**
 * User Feed Request
 */
public class InstagramUserFeedRequest extends InstagramGetRequest<InstagramFeedResult> {
	private long userId;
	private String maxId;
	private long minTimestamp;

	@Override
	public String getUrl() {
		return "feed/user/" + userId + "/?max_id=" + maxId + "&min_timestamp=" + minTimestamp + "&rank_token="
				+ api.getRankToken() + "&ranked_content=true&";
	}

	@Override

	public InstagramFeedResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, InstagramFeedResult.class);
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getMaxId() {
		return maxId;
	}

	public void setMaxId(String maxId) {
		this.maxId = maxId;
	}

	public long getMinTimestamp() {
		return minTimestamp;
	}

	public void setMinTimestamp(long minTimestamp) {
		this.minTimestamp = minTimestamp;
	}
}
