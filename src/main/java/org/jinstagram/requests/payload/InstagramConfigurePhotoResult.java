
package org.jinstagram.requests.payload;

/**
 * Configure Photo Result
 */
public class InstagramConfigurePhotoResult extends StatusResult {
	private InstagramFeedItem media;
	private String upload_id;

	public InstagramFeedItem getMedia() {
		return media;
	}

	public void setMedia(InstagramFeedItem media) {
		this.media = media;
	}

	public String getUpload_id() {
		return upload_id;
	}

	public void setUpload_id(String upload_id) {
		this.upload_id = upload_id;
	}
}
