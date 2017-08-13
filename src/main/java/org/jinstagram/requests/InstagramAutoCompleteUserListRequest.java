
package org.jinstagram.requests;

import org.jinstagram.requests.payload.StatusResult;

/**
 * Autocomplete User List Request
 * 
 * @author 
 *
 */
public class InstagramAutoCompleteUserListRequest extends InstagramGetRequest<StatusResult> {

    @Override
    public String getUrl() {
        return "friendships/autocomplete_user_list/";
    }

    @Override
    public String getPayload() {
        return null;
    }

    @Override
    public StatusResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, StatusResult.class);
    }

}
