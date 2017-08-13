
package org.jinstagram.requests;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.jinstagram.InstagramConstants;
import org.jinstagram.JInstagramException;
import org.jinstagram.util.InstagramHashUtil;

/**
 *
 */
public abstract class InstagramPostRequest<T> extends InstagramRequest<T> {
	@Override
	public String getMethod() {
		return "POST";
	}

	@Override
	public T execute() throws JInstagramException {
		try {
			HttpPost post = new HttpPost(InstagramConstants.API_URL + getUrl());
			post.addHeader("Connection", "close");
			post.addHeader("Accept", "*/*");
			post.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			post.addHeader("Cookie2", "$Version=1");
			post.addHeader("Accept-Language", "en-US");
			post.addHeader("User-Agent", InstagramConstants.USER_AGENT);

			log.debug("User-Agent: " + InstagramConstants.USER_AGENT);
			String parsedData = InstagramHashUtil.generateSignature(getPayload());
			log.debug("Signed data: " + parsedData);
			post.setEntity(new StringEntity(parsedData));

			HttpResponse response = api.getClient().execute(post);
			api.setLastResponse(response);

			int resultCode = response.getStatusLine().getStatusCode();
			String content = EntityUtils.toString(response.getEntity());

			post.releaseConnection();

			return parseResult(resultCode, content);
		} catch (ParseException | IOException e) {
			String msg = String.format("Error %s processing POST request", e.getMessage());
			throw new JInstagramException(msg, e);
		}
	}

}
