
package org.jinstagram.requests.payload;

public class InstagramComment {
	private long pk;
	private long user_id;
	private String text;
	private int type;
	private long created_at;
	private long created_at_utc;
	private String content_type;
	private String status;
	private int bit_flags;
	private InstagramUser user;

	public long getPk() {
		return pk;
	}

	public void setPk(long pk) {
		this.pk = pk;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}

	public long getCreated_at_utc() {
		return created_at_utc;
	}

	public void setCreated_at_utc(long created_at_utc) {
		this.created_at_utc = created_at_utc;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBit_flags() {
		return bit_flags;
	}

	public void setBit_flags(int bit_flags) {
		this.bit_flags = bit_flags;
	}

	public InstagramUser getUser() {
		return user;
	}

	public void setUser(InstagramUser user) {
		this.user = user;
	}
}
