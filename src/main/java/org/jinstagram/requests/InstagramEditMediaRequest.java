
package org.jinstagram.requests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jinstagram.JInstagramException;
import org.jinstagram.requests.payload.StatusResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request for editing media.
 */
public class InstagramEditMediaRequest extends InstagramPostRequest<StatusResult> {
	private String mediaId;
	private String captionText;

	@JsonProperty("usertags")
	private UserTags userTags;

	@Override
	public String getUrl() {
		return "media/" + mediaId + "/edit_media/";
	}

	@Override
	public String getPayload() throws JInstagramException {
		try {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("_uuid", api.getUuid());
			map.put("_uid", api.getUserId());
			map.put("_csrftoken", api.getOrFetchCsrf());
			map.put("caption_text", captionText);

			ObjectMapper mapper = new ObjectMapper();
			if (userTags != null) {
				map.put("usertags", mapper.writeValueAsString(userTags));
			}

			return mapper.writeValueAsString(map);
		} catch (IOException e) {
			throw new JInstagramException(e);
		}
	}

	@Override
	public StatusResult parseResult(int resultCode, String content) {
		return parseJson(resultCode, content, StatusResult.class);
	}

	public class UserTag {
		@JsonProperty("user_id")
		private String userId;
		private float[] position = { 0, 0 };

		public UserTag(String userId) {
			this.userId = userId;
		}

		public UserTag(String userId, float positionX, float positionY) {
			this(userId);
			this.position[0] = positionX;
			this.position[1] = positionY;
		}

		public void setPosition(float positionX, float positionY) {
			this.position[0] = positionX;
			this.position[1] = positionY;
		}

	}

	public class UserTags {
		@JsonProperty("removed")
		private List<String> userIdsToRemoveTag = new ArrayList<String>();
		@JsonProperty("in")
		private List<UserTag> tagsToAdd = new ArrayList<UserTag>();
	}
}
