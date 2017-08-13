
package org.jinstagram.requests;

import org.jinstagram.requests.payload.StatusResult;

/**
 * Timeline Feed Request
 *
 */
public class InstagramTimelineFeedRequest extends InstagramGetRequest<StatusResult> {

	@Override
	public String getUrl() {
		return "feed/timeline/";
	}

	@Override
	public String getPayload() {
		return null;
	}

	@Override

	public StatusResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, StatusResult.class);
	}
}
