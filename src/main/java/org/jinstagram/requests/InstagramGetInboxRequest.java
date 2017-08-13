
package org.jinstagram.requests;

import org.jinstagram.requests.payload.StatusResult;

/**
 * Inbox Feed Request
 *
 * 
 */
public class InstagramGetInboxRequest extends InstagramGetRequest<StatusResult> {

	@Override
	public String getUrl() {
		return "direct_v2/inbox/?";
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
