
package org.jinstagram.requests;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.util.EntityUtils;
import org.jinstagram.InstagramConstants;
import org.jinstagram.JInstagramException;
import org.jinstagram.requests.payload.StatusResult;
import org.jinstagram.util.InstagramGenericUtil;

/**
 * Direct-share request.
 *
 */
public class InstagramDirectShareRequest extends InstagramRequest<StatusResult> {
	private ShareType shareType;
	private List<String> recipients;
	/**
	 * The media ID in instagram's internal format (ie "223322332233_22332").
	 */
	private String mediaId;
	private String message;

	@Override
	public String getUrl() throws IllegalArgumentException {
		String result;
		switch (shareType) {
		case MESSAGE:
			result = "direct_v2/threads/broadcast/text/";
			break;
		case MEDIA:
			result = "direct_v2/threads/broadcast/media_share/?media_type=photo";
			break;
		default:
			throw new IllegalArgumentException("Invalid shareType parameter value: " + shareType);
		}
		return result;
	}

	@Override
	public String getMethod() {
		return "POST";
	}

	@Override
	public StatusResult execute() throws JInstagramException {
		String recipients = "\"" + String.join("\",\"", this.recipients.toArray(new String[0])) + "\"";
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();

		Map<String, String> map = new HashMap<String, String>();
		if (shareType == ShareType.MEDIA) {
			map.put("type", "form-data");
			map.put("name", "media_id");
			map.put("data", mediaId);
			data.add(map);
		}

		map = map.size() > 0 ? new HashMap<String, String>() : map;
		map.put("type", "form-data");
		map.put("name", "recipient_users");
		map.put("data", "[[" + recipients + "]]");
		data.add(map);

		map = new HashMap<String, String>();
		map.put("type", "form-data");
		map.put("name", "client_context");
		map.put("data", InstagramGenericUtil.generateUuid(true));
		data.add(map);

		map = new HashMap<String, String>();
		map.put("type", "form-data");
		map.put("name", "thread_ids");
		map.put("data", "[]");
		data.add(map);

		map = new HashMap<String, String>();
		map.put("type", "form-data");
		map.put("name", "text");
		map.put("data", message == null ? "" : message);
		data.add(map);

		HttpPost post = createHttpRequest();
		post.setEntity(new ByteArrayEntity(buildBody(data, api.getUuid()).getBytes(StandardCharsets.UTF_8)));

		try (CloseableHttpResponse response = api.getClient().execute(post)) {
			api.setLastResponse(response);

			int resultCode = response.getStatusLine().getStatusCode();
			String content = EntityUtils.toString(response.getEntity());

			log.info("Direct-share request result: " + resultCode + ", " + content);

			post.releaseConnection();

			StatusResult result = parseResult(resultCode, content);

			return result;
		} catch (IOException e) {
			throw new JInstagramException(e);
		} 
	}

	@Override
	public StatusResult parseResult(int resultCode, String content) {
		return parseJson(resultCode, content, StatusResult.class);
	}

	protected HttpPost createHttpRequest() {
		String url = InstagramConstants.API_URL + getUrl();
		log.info("Direct-share URL: " + url);

		HttpPost post = new HttpPost(url);
		post.addHeader("User-Agent", InstagramConstants.USER_AGENT);
		post.addHeader("Connection", "keep-alive");
		post.addHeader("Proxy-Connection", "keep-alive");
		post.addHeader("Accept", "*/*");
		post.addHeader("Content-Type", "multipart/form-data; boundary=" + api.getUuid());
		post.addHeader("Accept-Language", "en-US");
		return post;
	}

	protected String buildBody(List<Map<String, String>> bodies, String boundary) {
		StringBuilder sb = new StringBuilder();
		String newLine = "\r\n";
		for (Map<String, String> b : bodies) {
			sb.append("--").append(boundary).append(newLine).append("Content-Disposition: ").append(b.get("type"))
					.append("; name=\"").append(b.get("name")).append("\"").append(newLine).append(newLine)
					.append(b.get("data")).append(newLine);
		}
		sb.append("--").append(boundary).append("--");
		String body = sb.toString();

		log.debug("Direct-share message body: " + body);
		return body;
	}

	protected void init() {
		switch (shareType) {
		case MEDIA:
			if (mediaId == null || mediaId.isEmpty()) {
				throw new IllegalArgumentException("mediaId cannot be null or empty.");
			}
			break;
		case MESSAGE:
			if (message == null || message.isEmpty()) {
				throw new IllegalArgumentException("message cannot be null or empty.");
			}
			break;
		default:
			break;
		}
	}

	public static Builder builder(ShareType shareType, List<String> recipients) {
		Builder b = new Builder();
		b.shareType(shareType).recipients(recipients);
		return b;
	}

	public static class Builder extends DirectShareRequestBuilder {
		Builder() {
			super();
		}

		@Override
		public InstagramDirectShareRequest build() {
			InstagramDirectShareRequest i = super.build();
			i.init();
			return i;
		}
	}

	public enum ShareType {
		MESSAGE, MEDIA
	}

	public static class DirectShareRequestBuilder {
		private ShareType shareType;
		private List<String> recipients;
		private String mediaId;
		private String message;

		public DirectShareRequestBuilder shareType(ShareType shareType) {
			this.shareType = shareType;
			return this;
		}

		public DirectShareRequestBuilder recipients(List<String> recipients) {
			this.recipients = recipients;
			return this;
		}

		public DirectShareRequestBuilder mediaId(String mediaId) {
			this.mediaId = mediaId;
			return this;
		}

		public DirectShareRequestBuilder message(String message) {
			this.message = message;
			return this;
		}

		public InstagramDirectShareRequest build() {
			return new InstagramDirectShareRequest(this);
		}
	}

	private InstagramDirectShareRequest(DirectShareRequestBuilder builder) {
		this.shareType = builder.shareType;
		this.recipients = builder.recipients;
		this.mediaId = builder.mediaId;
		this.message = builder.message;
	}
}
