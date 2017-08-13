
package org.jinstagram.requests;

import org.jinstagram.requests.payload.InstagramLoginPayload;
import org.jinstagram.requests.payload.InstagramLoginResult;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Login Request
 * 
 * @author
 *
 */

public class InstagramLoginRequest extends InstagramPostRequest<InstagramLoginResult> {

	private InstagramLoginPayload payload;

	public InstagramLoginRequest(InstagramLoginPayload loginRequest) {
		this.payload = loginRequest;
	}

	@Override
	public String getUrl() {
		return "accounts/login/";
	}

	@Override
	public String getPayload() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String payloadJson = mapper.writeValueAsString(payload);

			return payloadJson;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public InstagramLoginResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, InstagramLoginResult.class);
	}

	@Override
	public boolean requiresLogin() {
		return false;
	}
}
