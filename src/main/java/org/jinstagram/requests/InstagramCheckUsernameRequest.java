
package org.jinstagram.requests;

import java.util.LinkedHashMap;
import java.util.Map;

import org.jinstagram.requests.payload.InstagramCheckUsernameResult;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Sync Features Request
 * 
 * @author
 *
 */
public class InstagramCheckUsernameRequest extends InstagramPostRequest<InstagramCheckUsernameResult> {
	private String username;

	@Override
	public String getUrl() {
		return "users/check_username/";
	}

	@Override

	public String getPayload() {
		try {
			ObjectMapper mapper = new ObjectMapper();

			Map<String, Object> payloadMap = new LinkedHashMap<>();
			payloadMap.put("username", username);
			payloadMap.put("_csrftoken", api.getOrFetchCsrf());

			String payloadJson = mapper.writeValueAsString(payloadMap);

			return payloadJson;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public InstagramCheckUsernameResult parseResult(int statusCode, String content) {
		return this.parseJson(content, InstagramCheckUsernameResult.class);
	}

	@Override
	public boolean requiresLogin() {
		return false;
	}
}
