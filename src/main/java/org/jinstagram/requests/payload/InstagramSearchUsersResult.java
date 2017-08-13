
package org.jinstagram.requests.payload;

import java.util.List;

/**
 * Search Users Result
 */
public class InstagramSearchUsersResult extends StatusResult {
	private List<InstagramSearchUsersResultUser> users;
	private boolean has_more;
	private int num_results;

	public List<InstagramSearchUsersResultUser> getUsers() {
		return users;
	}

	public void setUsers(List<InstagramSearchUsersResultUser> users) {
		this.users = users;
	}

	public boolean isHas_more() {
		return has_more;
	}

	public void setHas_more(boolean has_more) {
		this.has_more = has_more;
	}

	public int getNum_results() {
		return num_results;
	}

	public void setNum_results(int num_results) {
		this.num_results = num_results;
	}
}
