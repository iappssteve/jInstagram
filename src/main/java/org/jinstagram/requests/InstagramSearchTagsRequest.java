
package org.jinstagram.requests;

import org.jinstagram.requests.payload.InstagramSearchTagsResult;

/**
 * Search Tags Request
 * 
 */
public class InstagramSearchTagsRequest extends InstagramGetRequest<InstagramSearchTagsResult> {

	private String query;

	@Override
	public String getUrl() {
		return "tags/search/?is_typeahead=true&q=" + query + "&rank_token=" + api.getRankToken();
	}

	@Override
	public String getPayload() {
		return null;
	}

	@Override

	public InstagramSearchTagsResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, InstagramSearchTagsResult.class);
	}

}
