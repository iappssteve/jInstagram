
package org.jinstagram.requests;

import org.jinstagram.requests.payload.InstagramFeedResult;

/**
 * Tag Feed Request
 * 
 * @author
 *
 */
public class InstagramTagFeedRequest extends InstagramGetRequest<InstagramFeedResult> {
	private String tag = "";
	private String maxId;

	@Override
	public String getUrl() {
		String url = "feed/tag/" + tag + "/?rank_token=" + api.getRankToken() + "&ranked_content=true&";
		if (maxId != null && !maxId.isEmpty()) {
			url += "max_id=" + maxId;
		}
		return url;
	}

	@Override
	public InstagramFeedResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, InstagramFeedResult.class);
	}
}
