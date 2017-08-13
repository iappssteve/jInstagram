
package org.jinstagram.requests;

import org.jinstagram.requests.payload.InstagramSyncFeaturesPayload;
import org.jinstagram.requests.payload.InstagramSyncFeaturesResult;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Sync Features Request
 * 
 * @author
 *
 */
public class InstagramSyncFeaturesRequest extends InstagramPostRequest<InstagramSyncFeaturesResult> {
	private InstagramSyncFeaturesPayload payload;

	public InstagramSyncFeaturesRequest(InstagramSyncFeaturesPayload syncFeatures) {
		this.payload = syncFeatures;
	}

	@Override
	public String getUrl() {
		return "qe/sync/";
	}

	@Override
	public String getPayload() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String payloadJson = mapper.writeValueAsString(payload);

			return payloadJson;
		} catch (Exception e) {
		}

		return null;
	}

	@Override

	public InstagramSyncFeaturesResult parseResult(int statusCode, String content) {
		return new InstagramSyncFeaturesResult();
	}

}
