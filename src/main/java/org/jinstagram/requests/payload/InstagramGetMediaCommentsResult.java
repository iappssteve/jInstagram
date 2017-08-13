
package org.jinstagram.requests.payload;

import java.util.List;

/**
 * Response from media comments request
 */
public class InstagramGetMediaCommentsResult extends StatusResult {
	private boolean comment_likes_enabled;
	private int comment_count;
	private boolean caption_is_edited;
	private boolean has_more_comments;
	private boolean has_more_headload_comments;
	private String next_max_id;
	private List<InstagramComment> comments;

	public boolean isComment_likes_enabled() {
		return comment_likes_enabled;
	}

	public void setComment_likes_enabled(boolean comment_likes_enabled) {
		this.comment_likes_enabled = comment_likes_enabled;
	}

	public int getComment_count() {
		return comment_count;
	}

	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}

	public boolean isCaption_is_edited() {
		return caption_is_edited;
	}

	public void setCaption_is_edited(boolean caption_is_edited) {
		this.caption_is_edited = caption_is_edited;
	}

	public boolean isHas_more_comments() {
		return has_more_comments;
	}

	public void setHas_more_comments(boolean has_more_comments) {
		this.has_more_comments = has_more_comments;
	}

	public boolean isHas_more_headload_comments() {
		return has_more_headload_comments;
	}

	public void setHas_more_headload_comments(boolean has_more_headload_comments) {
		this.has_more_headload_comments = has_more_headload_comments;
	}

	public String getNext_max_id() {
		return next_max_id;
	}

	public void setNext_max_id(String next_max_id) {
		this.next_max_id = next_max_id;
	}

	public List<InstagramComment> getComments() {
		return comments;
	}

	public void setComments(List<InstagramComment> comments) {
		this.comments = comments;
	}
}
