
package org.jinstagram.requests.internal;

import org.jinstagram.requests.InstagramGetRequest;
import org.jinstagram.requests.payload.StatusResult;
import org.jinstagram.util.InstagramGenericUtil;

/**
 * Fetch Headers Request
 * 
 * @author
 *
 */
public class InstagramFetchHeadersRequest extends InstagramGetRequest<StatusResult> {
	@Override
	public String getUrl() {
		return "si/fetch_headers/?challenge_type=signup&guid=" + InstagramGenericUtil.generateUuid(false);
	}

	@Override
	public String getPayload() {
		return null;
	}

	@Override
	public boolean requiresLogin() {
		return false;
	}

	@Override

	public StatusResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, StatusResult.class);
	}
}
