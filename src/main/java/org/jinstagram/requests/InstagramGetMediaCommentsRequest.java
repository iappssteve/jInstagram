
package org.jinstagram.requests;

import org.jinstagram.InstagramConstants;
import org.jinstagram.requests.payload.InstagramGetMediaCommentsResult;

/**
 * Get media comments request
 * 
 * @author Evgeny Bondarenko (evgbondarenko@gmail.com)
 *
 */
public class InstagramGetMediaCommentsRequest extends InstagramGetRequest<InstagramGetMediaCommentsResult> {
	private String mediaId;
	private String maxId;

	public InstagramGetMediaCommentsRequest(String mediaId, String maxId) {
		super();
		this.mediaId = mediaId;
		this.maxId = maxId;
	}

	@Override
	public String getUrl() {
		String url = "media/" + mediaId + "/comments/?ig_sig_key_version=" + InstagramConstants.API_KEY_VERSION;
		if (maxId != null && !maxId.isEmpty()) {
			url += "&max_id=" + maxId;
		}
		return url;
	}

	@Override
	public InstagramGetMediaCommentsResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, InstagramGetMediaCommentsResult.class);
	}
}