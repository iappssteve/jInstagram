
package org.jinstagram.requests;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jinstagram.JInstagramException;
import org.jinstagram.requests.payload.InstagramPostCommentResult;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Comment Post Request
 * 
 * @author
 *
 */

public class InstagramPostCommentRequest extends InstagramPostRequest<InstagramPostCommentResult> {

	private long mediaId;
	private String commentText;

	@Override
	public String getUrl() {
		return "media/" + mediaId + "/comment/";
	}

	@Override

	public String getPayload() throws JInstagramException {
		try {
			Map<String, Object> likeMap = new LinkedHashMap<>();
			likeMap.put("_uuid", api.getUuid());
			likeMap.put("_uid", api.getUserId());
			likeMap.put("_csrftoken", api.getOrFetchCsrf());
			likeMap.put("comment_text", commentText);

			ObjectMapper mapper = new ObjectMapper();
			String payloadJson = mapper.writeValueAsString(likeMap);

			return payloadJson;
		} catch (IOException e) {
			throw new JInstagramException(e);
		}
	}

	@Override

	public InstagramPostCommentResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, InstagramPostCommentResult.class);
	}
}
