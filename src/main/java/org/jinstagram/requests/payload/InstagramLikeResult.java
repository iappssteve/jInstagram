
package org.jinstagram.requests.payload;

/**
 * Status Result
 * 
 */
public class InstagramLikeResult extends StatusResult {
	private boolean spam;
	private String feedback_ignore_label;
	private String feedback_title;
	private String feedback_message;
	private String feedback_url;
	private String feedback_action;
	private String feedback_appeal_label;

	@Override
	public boolean isSpam() {
		return spam;
	}

	@Override
	public void setSpam(boolean spam) {
		this.spam = spam;
	}

	public String getFeedback_ignore_label() {
		return feedback_ignore_label;
	}

	public void setFeedback_ignore_label(String feedback_ignore_label) {
		this.feedback_ignore_label = feedback_ignore_label;
	}

	@Override
	public String getFeedback_title() {
		return feedback_title;
	}

	@Override
	public void setFeedback_title(String feedback_title) {
		this.feedback_title = feedback_title;
	}

	@Override
	public String getFeedback_message() {
		return feedback_message;
	}

	@Override
	public void setFeedback_message(String feedback_message) {
		this.feedback_message = feedback_message;
	}

	public String getFeedback_url() {
		return feedback_url;
	}

	public void setFeedback_url(String feedback_url) {
		this.feedback_url = feedback_url;
	}

	public String getFeedback_action() {
		return feedback_action;
	}

	public void setFeedback_action(String feedback_action) {
		this.feedback_action = feedback_action;
	}

	public String getFeedback_appeal_label() {
		return feedback_appeal_label;
	}

	public void setFeedback_appeal_label(String feedback_appeal_label) {
		this.feedback_appeal_label = feedback_appeal_label;
	}
}