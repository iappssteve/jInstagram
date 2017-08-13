
package org.jinstagram.requests.payload;

import java.util.List;
import java.util.Map;

/**
 * Upload Video Result
 * 
 * @author 
 *
 */
public class InstagramUploadVideoResult extends StatusResult {
    private String upload_id;
    private List<Map<String, Object>> video_upload_urls;
    
    
	@Override
	public String toString() {
		return "InstagramUploadVideoResult [upload_id=" + upload_id + ", video_upload_urls=" + video_upload_urls
				+ ", toString()=" + super.toString() + "]";
	}


	public String getUpload_id() {
		return upload_id;
	}


	public void setUpload_id(String upload_id) {
		this.upload_id = upload_id;
	}


	public List<Map<String, Object>> getVideo_upload_urls() {
		return video_upload_urls;
	}
}