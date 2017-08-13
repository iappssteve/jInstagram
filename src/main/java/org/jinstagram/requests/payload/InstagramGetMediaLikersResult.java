
package org.jinstagram.requests.payload;

import java.util.List;

/**
 * Get Media Likers Result
 *
 */
public class InstagramGetMediaLikersResult extends StatusResult {
	public int user_count;

	public List<InstagramUserSummary> users;
}