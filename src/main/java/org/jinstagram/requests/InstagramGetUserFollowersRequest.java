
package org.jinstagram.requests;

import org.jinstagram.requests.payload.InstagramGetUserFollowersResult;

/**
 * Get User Followers Request
 * 
 * @author 
 *
 */
public class InstagramGetUserFollowersRequest extends InstagramGetRequest<InstagramGetUserFollowersResult> {
    private long userId;
    private String maxId;

    @Override
    public String getUrl() {
        String baseUrl = "friendships/" + userId + "/followers/?rank_token=" + api.getRankToken();
        if (maxId != null && !maxId.isEmpty()) {
            baseUrl += "&max_id=" + maxId;
        }
        
        return baseUrl;
    }

    @Override

    public InstagramGetUserFollowersResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramGetUserFollowersResult.class);
    }

}
