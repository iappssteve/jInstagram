
package org.jinstagram.requests;

import java.util.LinkedHashMap;
import java.util.Map;

import org.jinstagram.JInstagramException;
import org.jinstagram.requests.payload.InstagramLikeResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Like Request
 * 
 * @author
 *
 */

public class InstagramLikeRequest extends InstagramPostRequest<InstagramLikeResult> {

	private long mediaId;

	@Override
	public String getUrl() {
		return "media/" + mediaId + "/like/";
	}

	@Override

	public String getPayload() throws JInstagramException {
		try {
			Map<String, Object> likeMap = new LinkedHashMap<>();
			likeMap.put("_uuid", api.getUuid());
			likeMap.put("_uid", api.getUserId());
			likeMap.put("_csrftoken", api.getOrFetchCsrf());
			likeMap.put("media_id", mediaId);

			ObjectMapper mapper = new ObjectMapper();
			String payloadJson = mapper.writeValueAsString(likeMap);

			return payloadJson;
		} catch (JsonProcessingException e) {
			throw new JInstagramException(e);
		}
	}

	@Override

	public InstagramLikeResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, InstagramLikeResult.class);
	}
}
