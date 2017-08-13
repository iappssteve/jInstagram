
package org.jinstagram.requests.payload;

/**
 * Status Result
 * @author 
 *
 */
public class StatusResult {
    private String status;
    private String message;
    
    private boolean spam;
    private String feedback_title;
    private String feedback_message;
    
    public StatusResult(String status) {
		super();
		this.status = status;
	}
    
	public StatusResult() {
		super();
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSpam() {
		return spam;
	}
	public void setSpam(boolean spam) {
		this.spam = spam;
	}
	public String getFeedback_title() {
		return feedback_title;
	}
	public void setFeedback_title(String feedback_title) {
		this.feedback_title = feedback_title;
	}
	public String getFeedback_message() {
		return feedback_message;
	}
	public void setFeedback_message(String feedback_message) {
		this.feedback_message = feedback_message;
	}
}