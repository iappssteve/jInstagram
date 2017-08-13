
package org.jinstagram.requests.payload;

/**
 * Logged User VO
 * 
 */
public class InstagramLoggedUser {
	public String profile_pic_url;
	public boolean allow_contacts_sync;
	public String username;
	public String full_name;
	public boolean is_private;
	public String profile_pic_id;
	public long pk;
	public boolean is_verified;
	public boolean has_anonymous_profile_picture;

	public String getProfile_pic_url() {
		return profile_pic_url;
	}

	public void setProfile_pic_url(String profile_pic_url) {
		this.profile_pic_url = profile_pic_url;
	}

	public boolean isAllow_contacts_sync() {
		return allow_contacts_sync;
	}

	public void setAllow_contacts_sync(boolean allow_contacts_sync) {
		this.allow_contacts_sync = allow_contacts_sync;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public boolean isIs_private() {
		return is_private;
	}

	public void setIs_private(boolean is_private) {
		this.is_private = is_private;
	}

	public String getProfile_pic_id() {
		return profile_pic_id;
	}

	public void setProfile_pic_id(String profile_pic_id) {
		this.profile_pic_id = profile_pic_id;
	}

	public long getPk() {
		return pk;
	}

	public void setPk(long pk) {
		this.pk = pk;
	}

	public boolean isIs_verified() {
		return is_verified;
	}

	public void setIs_verified(boolean is_verified) {
		this.is_verified = is_verified;
	}

	public boolean isHas_anonymous_profile_picture() {
		return has_anonymous_profile_picture;
	}

	public void setHas_anonymous_profile_picture(boolean has_anonymous_profile_picture) {
		this.has_anonymous_profile_picture = has_anonymous_profile_picture;
	}

	@Override
	public String toString() {
		return "InstagramLoggedUser [profile_pic_url=" + profile_pic_url + ", allow_contacts_sync="
				+ allow_contacts_sync + ", username=" + username + ", full_name=" + full_name + ", is_private="
				+ is_private + ", profile_pic_id=" + profile_pic_id + ", pk=" + pk + ", is_verified=" + is_verified
				+ ", has_anonymous_profile_picture=" + has_anonymous_profile_picture + "]";
	}
}