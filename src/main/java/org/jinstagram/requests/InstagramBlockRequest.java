
package org.jinstagram.requests;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jinstagram.requests.payload.StatusResult;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Block Request
 * 
 * @author
 *
 */
public class InstagramBlockRequest extends InstagramPostRequest<StatusResult> {
	protected Logger log = Logger.getLogger(InstagramBlockRequest.class);
	private long userId;

	public InstagramBlockRequest(long userId) {
		super();
		this.userId = userId;
	}

	@Override
	public String getUrl() {
		return "friendships/block/" + userId + "/";
	}

	@Override

	public String getPayload() {
		try {
			Map<String, Object> likeMap = new LinkedHashMap<>();
			likeMap.put("_uuid", api.getUuid());
			likeMap.put("_uid", api.getUserId());
			likeMap.put("user_id", userId);
			likeMap.put("_csrftoken", api.getOrFetchCsrf());

			ObjectMapper mapper = new ObjectMapper();
			String payloadJson = mapper.writeValueAsString(likeMap);

			return payloadJson;
		} catch (Exception e) {
			return null;
		}
	}

	@Override

	public StatusResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, StatusResult.class);
	}

}
