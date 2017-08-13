
package org.jinstagram.requests;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.jinstagram.InstagramConstants;
import org.jinstagram.JInstagramException;

/**
 * 
 * @author brunovolpato
 *
 */
public abstract class InstagramGetRequest<T> extends InstagramRequest<T> {

    @Override
    public String getMethod() {
        return "GET";
    }
    
    @Override
    public T execute() throws JInstagramException {
        HttpGet get = new HttpGet(InstagramConstants.API_URL + getUrl());
        get.addHeader("Connection", "close");
        get.addHeader("Accept", "*/*");
        get.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        get.addHeader("Cookie2", "$Version=1");
        get.addHeader("Accept-Language", "en-US");
        get.addHeader("User-Agent", InstagramConstants.USER_AGENT);
        
        try {
			HttpResponse response = api.getClient().execute(get);
			api.setLastResponse(response);
			
			int resultCode = response.getStatusLine().getStatusCode();
			String content = EntityUtils.toString(response.getEntity());
			
			get.releaseConnection();

			return parseResult(resultCode, content);
		} catch (ParseException | IOException e) {
			throw new JInstagramException(e);
		}
    }
    
    @Override
    public String getPayload() {
        return null;
    }

    
}
