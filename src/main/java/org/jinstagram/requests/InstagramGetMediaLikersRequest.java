
package org.jinstagram.requests;

import org.jinstagram.requests.payload.InstagramGetMediaLikersResult;

/**
 * Like Request
 *
 */
public class InstagramGetMediaLikersRequest extends InstagramGetRequest<InstagramGetMediaLikersResult> {

	private long mediaId;

	@Override
	public String getUrl() {
		return "media/" + mediaId + "/likers/";
	}

	@Override

	public InstagramGetMediaLikersResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, InstagramGetMediaLikersResult.class);
	}
}
