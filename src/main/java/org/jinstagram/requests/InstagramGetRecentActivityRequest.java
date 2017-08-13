
package org.jinstagram.requests;

import org.jinstagram.requests.payload.StatusResult;

/**
 * Recent Activity Request
 *
 */
public class InstagramGetRecentActivityRequest extends InstagramGetRequest<StatusResult> {

	@Override
	public String getUrl() {
		return "news/inbox/?";
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
