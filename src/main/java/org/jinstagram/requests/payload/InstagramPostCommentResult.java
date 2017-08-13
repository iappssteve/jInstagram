
package org.jinstagram.requests.payload;

/**
 * Post Comment Result
 * 
 */
public class InstagramPostCommentResult extends StatusResult {
	private InstagramComment comment;

	public InstagramComment getComment() {
		return comment;
	}

	public void setComment(InstagramComment comment) {
		this.comment = comment;
	}
}