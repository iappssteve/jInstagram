
package org.jinstagram.requests.internal;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jinstagram.JInstagramException;
import org.jinstagram.requests.InstagramPostRequest;
import org.jinstagram.requests.payload.StatusResult;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Expose Request
 * 
 * @author
 *
 */

public class InstagramExposeRequest extends InstagramPostRequest<StatusResult> {
	@Override
	public String getUrl() {
		return "qe/expose/";
	}

	@Override

	public String getPayload() throws JInstagramException {
		try {
			Map<String, Object> likeMap = new LinkedHashMap<>();
			likeMap.put("_uuid", api.getUuid());
			likeMap.put("_uid", api.getUserId());
			likeMap.put("id", api.getUserId());
			likeMap.put("_csrftoken", api.getOrFetchCsrf());
			likeMap.put("experiment", "ig_android_profile_contextual_feed");

			ObjectMapper mapper = new ObjectMapper();
			String payloadJson = mapper.writeValueAsString(likeMap);

			return payloadJson;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override

	public StatusResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, StatusResult.class);
	}
}
