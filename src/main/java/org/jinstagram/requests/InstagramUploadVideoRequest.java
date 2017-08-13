
package org.jinstagram.requests;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.jinstagram.InstagramConstants;
import org.jinstagram.JInstagramException;
import org.jinstagram.requests.internal.InstagramConfigureVideoRequest;
import org.jinstagram.requests.internal.InstagramExposeRequest;
import org.jinstagram.requests.internal.InstagramUploadVideoJobRequest;
import org.jinstagram.requests.payload.InstagramUploadVideoResult;
import org.jinstagram.requests.payload.StatusResult;

/**
 * Upload Video request
 * @author 
 *
 */
public class InstagramUploadVideoRequest extends InstagramRequest<StatusResult> {
	public static class VideoDescriptor {
		private int frameWidth;
		private int frameHeight;
		private int duration;
		private String caption;
		private File videoFile;
		private File thumbnailFile;
		
		public int getFrameWidth() {
			return frameWidth;
		}
		public int getFrameHeight() {
			return frameHeight;
		}
		public int getDuration() {
			return duration;
		}
		public String getCaption() {
			return caption;
		}
		public File getVideoFile() {
			return videoFile;
		}
		public File getThumbnailFile() {
			return thumbnailFile;
		}
		public void setFrameWidth(int frameWidth) {
			this.frameWidth = frameWidth;
		}
		public void setFrameHeight(int frameHeight) {
			this.frameHeight = frameHeight;
		}
		public void setDuration(int duration) {
			this.duration = duration;
		}
		public void setCaption(String caption) {
			this.caption = caption;
		}
		public void setVideoFile(File videoFile) {
			this.videoFile = videoFile;
		}
		public void setThumbnailFile(File thumbnailFile) {
			this.thumbnailFile = thumbnailFile;
		}

		public boolean isValid() {
			return frameHeight > 0 && frameWidth > 0 && duration > 0 && videoFile != null 
					 && videoFile.exists() && thumbnailFile != null && thumbnailFile.exists();
		}
	}

	private VideoDescriptor descriptor;
	
    public InstagramUploadVideoRequest(VideoDescriptor descriptor) throws JInstagramException {
		super();
		if (descriptor.isValid()) {
			this.descriptor = descriptor;
		} else {
			throw new JInstagramException("Invalid video descriptor provided");
		}
		this.descriptor = descriptor;
	}

	@Override
    public String getUrl() {
        return "upload/video/";
    }

    @Override
    public String getMethod() {
        return "POST";
    }
    
    @Override
    public StatusResult execute() throws JInstagramException {
        
        HttpPost post = createHttpRequest();
        
        String uploadId = String.valueOf(System.currentTimeMillis());
        
        try (CloseableHttpResponse response = api.getClient().execute(post)) {
        		post.setEntity(createMultipartEntity(uploadId));
            api.setLastResponse(response);
            
            int resultCode = response.getStatusLine().getStatusCode();
            String content = EntityUtils.toString(response.getEntity());
            log.info("First phase result " + resultCode + ": " + content);
            
            post.releaseConnection();
            
            InstagramUploadVideoResult firstPhaseResult = parseJson(content, InstagramUploadVideoResult.class);
            
            if (!firstPhaseResult.getStatus().equalsIgnoreCase("ok")) {
                throw new RuntimeException("Error happened in video upload session start: " + firstPhaseResult.getMessage());
            }
    
            
            String uploadUrl = firstPhaseResult.getVideo_upload_urls().get(3).get("url").toString();
            String uploadJob = firstPhaseResult.getVideo_upload_urls().get(3).get("job").toString();
            
            StatusResult uploadJobResult = api.sendRequest(new InstagramUploadVideoJobRequest(uploadId, uploadUrl, uploadJob, this.descriptor.getVideoFile()));
            log.info("Upload result: " + uploadJobResult);
            
            if (!uploadJobResult.getStatus().equalsIgnoreCase("ok")) {
                throw new RuntimeException("Error happened in video upload submit job: " + uploadJobResult.getMessage());
            }
            
                
           /* StatusResult thumbnailResult = configureThumbnail(uploadId);
            
            if (!thumbnailResult.getStatus().equalsIgnoreCase("ok")) {
                throw new IllegalArgumentException("Failed to configure thumbnail: " + thumbnailResult.getMessage());
            }*/
            
            return api.sendRequest(new InstagramExposeRequest());
        } catch (IOException e) {
        		throw new JInstagramException(e);
		}
    }

    /**
     * Configures the thumbnails for the given uploadId
     * @param uploadId The session id
     * @return Result
     * @throws Exception
     * @throws IOException
     * @throws ClientProtocolException
     */
    protected StatusResult configureThumbnail(String uploadId) throws Exception, IOException, ClientProtocolException {
        if (descriptor.getThumbnailFile() == null) {            
        		throw new Exception("Thumbnail file is missing");
        }

        StatusResult thumbnailResult = api.sendRequest(new InstagramUploadPhotoRequest(descriptor.getThumbnailFile(), descriptor.getCaption(), uploadId));
        log.info("Thumbnail result: " + thumbnailResult);
        
        StatusResult configureResult = api.sendRequest(InstagramConfigureVideoRequest.builder().uploadId(uploadId)
                    .caption(descriptor.getCaption())
                    .duration(descriptor.getDuration())
                    .width(descriptor.getFrameWidth())
                    .height(descriptor.getFrameHeight())
                    .build());
        
        log.info("Video configure result: " + configureResult);

        return configureResult;
    }

    /**
     * Create the required multipart entity
     * @param uploadId Session ID
     * @return Entity to submit to the upload
     * @throws ClientProtocolException
     * @throws IOException
     */
    protected HttpEntity createMultipartEntity(String uploadId) throws JInstagramException {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("upload_id", uploadId);
        builder.addTextBody("_uuid", api.getUuid());
        builder.addTextBody("_csrftoken", api.getOrFetchCsrf());
        builder.addTextBody("media_type", "2");
        builder.setBoundary(api.getUuid());

        HttpEntity entity = builder.build();
        return entity;
    }

    /**
     * @return http request 
     */
    protected HttpPost createHttpRequest() {
        String url = InstagramConstants.API_URL + getUrl();
        log.info("URL Upload: " + url);
        
        HttpPost post = new HttpPost(url);
        post.addHeader("X-IG-Capabilities", "3Q4=");
        post.addHeader("X-IG-Connection-Type", "WIFI");
        post.addHeader("Host", "i.instagram.com");
        post.addHeader("Cookie2", "$Version=1");
        post.addHeader("Accept-Language", "en-US");
        post.addHeader("Connection", "close");
        post.addHeader("Content-Type", "multipart/form-data; boundary=" + api.getUuid());
        post.addHeader("User-Agent", InstagramConstants.USER_AGENT);
        return post;
    }

    @Override
    public String getPayload() {
        return null;
    }

    @Override
    public StatusResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, StatusResult.class);
    }
    
    public VideoDescriptor getDescriptor() {
    		return this.descriptor;
    }
}
