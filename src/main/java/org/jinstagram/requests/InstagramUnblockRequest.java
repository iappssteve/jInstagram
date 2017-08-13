
package org.jinstagram.requests;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jinstagram.JInstagramException;
import org.jinstagram.requests.payload.StatusResult;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Unblock Request
 * 
 * @author
 *
 */
public class InstagramUnblockRequest extends InstagramPostRequest<StatusResult> {

	private long userId;

	@Override
	public String getUrl() {
		return "friendships/unblock/" + userId + "/";
	}

	@Override

	public String getPayload() throws JInstagramException {
		try {
			Map<String, Object> likeMap = new LinkedHashMap<>();
			likeMap.put("_uuid", api.getUuid());
			likeMap.put("_uid", api.getUserId());
			likeMap.put("user_id", userId);
			likeMap.put("_csrftoken", api.getOrFetchCsrf());

			ObjectMapper mapper = new ObjectMapper();
			String payloadJson = mapper.writeValueAsString(likeMap);

			return payloadJson;
		} catch (IOException e) {
			throw new JInstagramException(e);
		}
	}

	@Override

	public StatusResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, StatusResult.class);
	}

}
