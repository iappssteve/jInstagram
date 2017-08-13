
package org.jinstagram.requests.internal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.jinstagram.InstagramConstants;
import org.jinstagram.requests.InstagramPostRequest;
import org.jinstagram.requests.payload.InstagramConfigurePhotoResult;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Like Request
 *
 */
public class InstagramConfigurePhotoRequest extends InstagramPostRequest<InstagramConfigurePhotoResult> {

	private File mediaFile;
	private String uploadId;
	private String caption;

	public InstagramConfigurePhotoRequest(File imageFile, String uploadId, String caption) {
		this.mediaFile = imageFile;
		this.uploadId = uploadId;
		this.caption = caption;
	}

	@Override
	public String getUrl() {
		return "media/configure/?";
	}

	@Override

	public String getPayload() {
		try {
			Map<String, Object> likeMap = new LinkedHashMap<>();
			likeMap.put("_csrftoken", api.getOrFetchCsrf());
			likeMap.put("media_folder", "Instagram");
			likeMap.put("source_type", 4);
			likeMap.put("_uid", api.getUserId());
			likeMap.put("_uuid", api.getUuid());
			likeMap.put("caption", caption);
			likeMap.put("upload_id", uploadId);

			Map<String, Object> deviceMap = new LinkedHashMap<>();
			deviceMap.put("manufacturer", InstagramConstants.DEVICE_MANUFACTURER);
			deviceMap.put("model", InstagramConstants.DEVICE_MODEL);
			deviceMap.put("android_version", InstagramConstants.DEVICE_ANDROID_VERSION);
			deviceMap.put("android_release", InstagramConstants.DEVICE_ANDROID_RELEASE);
			likeMap.put("device", deviceMap);

			BufferedImage image = ImageIO.read(mediaFile);

			Map<String, Object> editsMap = new LinkedHashMap<>();
			editsMap.put("crop_original_size", Arrays.asList((double) image.getWidth(), (double) image.getHeight()));
			editsMap.put("crop_center", Arrays.asList((double) 0, (double) 0));
			editsMap.put("crop_zoom", 1.0);
			likeMap.put("edits", editsMap);

			Map<String, Object> extraMap = new LinkedHashMap<>();
			extraMap.put("source_width", image.getWidth());
			extraMap.put("source_height", image.getHeight());
			likeMap.put("extra", extraMap);

			ObjectMapper mapper = new ObjectMapper();
			String payloadJson = mapper.writeValueAsString(likeMap);

			return payloadJson;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override

	public InstagramConfigurePhotoResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, InstagramConfigurePhotoResult.class);
	}

}
