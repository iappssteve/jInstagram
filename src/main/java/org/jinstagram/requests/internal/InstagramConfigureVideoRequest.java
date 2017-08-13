
package org.jinstagram.requests.internal;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jinstagram.InstagramConstants;
import org.jinstagram.JInstagramException;
import org.jinstagram.requests.InstagramPostRequest;
import org.jinstagram.requests.payload.StatusResult;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Like Request
 * 
 * @author
 *
 */
public class InstagramConfigureVideoRequest extends InstagramPostRequest<StatusResult> {
	private String uploadId;
	private String caption;

	private long duration;
	private int width;
	private int height;

	@Override
	public String getUrl() {
		return "media/configure/?video=1";
	}

	@Override

	public String getPayload() throws JInstagramException {

		try {
			Map<String, Object> likeMap = new LinkedHashMap<>();
			likeMap.put("upload_id", uploadId);
			likeMap.put("source_type", 3);
			likeMap.put("poster_frame_index", 0);
			likeMap.put("length", 0.0);
			likeMap.put("audio_muted", false);
			likeMap.put("filter_type", 0);
			likeMap.put("video_result", "deprecated");

			Map<String, Object> clips = new LinkedHashMap<>();
			clips.put("length", duration);
			clips.put("source_type", 3);
			clips.put("camera_position", "back");
			likeMap.put("clips", "clips");

			Map<String, Object> extraMap = new LinkedHashMap<>();
			extraMap.put("source_width", width);
			extraMap.put("source_height", height);
			likeMap.put("extra", extraMap);

			Map<String, Object> deviceMap = new LinkedHashMap<>();
			deviceMap.put("manufacturer", InstagramConstants.DEVICE_MANUFACTURER);
			deviceMap.put("model", InstagramConstants.DEVICE_MODEL);
			deviceMap.put("android_version", InstagramConstants.DEVICE_ANDROID_VERSION);
			deviceMap.put("android_release", InstagramConstants.DEVICE_ANDROID_RELEASE);
			likeMap.put("device", deviceMap);

			likeMap.put("_csrftoken", api.getOrFetchCsrf());
			likeMap.put("_uuid", api.getUuid());
			likeMap.put("_uid", api.getUserId());
			likeMap.put("caption", caption);

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

	public static class Builder {
		private String uploadId;
		private String caption;
		private long duration;
		private int width;
		private int height;

		public Builder uploadId(String uploadId) {
			this.uploadId = uploadId;
			return this;
		}

		public Builder caption(String caption) {
			this.caption = caption;
			return this;
		}

		public Builder duration(long duration) {
			this.duration = duration;
			return this;
		}

		public Builder width(int width) {
			this.width = width;
			return this;
		}

		public Builder height(int height) {
			this.height = height;
			return this;
		}

		public InstagramConfigureVideoRequest build() {
			return new InstagramConfigureVideoRequest(this);
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	private InstagramConfigureVideoRequest(Builder builder) {
		this.uploadId = builder.uploadId;
		this.caption = builder.caption;
		this.duration = builder.duration;
		this.width = builder.width;
		this.height = builder.height;
	}
}
