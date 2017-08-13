
package org.jinstagram.requests;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.jinstagram.JInstagram;
import org.jinstagram.JInstagramException;
import org.jinstagram.requests.payload.StatusResult;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class InstagramRequest<T> {
	protected Logger log = Logger.getLogger(InstagramRequest.class);

	public InstagramRequest() {
		super();
	}

	public InstagramRequest(JInstagram api) {
		super();
		this.api = api;
	}

	@JsonIgnore
	protected JInstagram api;

	/**
	 * @return the url
	 */
	public abstract String getUrl();

	/**
	 * @return the method
	 */
	public abstract String getMethod();

	/**
	 * @return the payload
	 */
	public String getPayload() throws JInstagramException {
		return null;
	}

	/**
	 * @return the result
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public abstract T execute() throws JInstagramException;

	/**
	 * Process response
	 * 
	 * @param resultCode
	 *            Status Code
	 * @param content
	 *            Content
	 */
	public abstract T parseResult(int resultCode, String content);

	/**
	 * @return if request must be logged in
	 */
	public boolean requiresLogin() {
		return true;
	}

	/**
	 * Parses Json into type, considering the status code
	 * 
	 * @param statusCode
	 *            HTTP Status Code
	 * @param str
	 *            Entity content
	 * @param clazz
	 *            Class
	 * @return Result
	 */

	public <U> U parseJson(int statusCode, String str, Class<U> clazz) {

		try {
			if (clazz.isAssignableFrom(StatusResult.class)) {

				// TODO: implement a better way to handle exceptions
				if (statusCode == HttpStatus.SC_NOT_FOUND) {
					StatusResult result = (StatusResult) clazz.newInstance();
					result.setStatus("error");
					result.setMessage("SC_NOT_FOUND");
					return (U) result;
				} else if (statusCode == HttpStatus.SC_FORBIDDEN) {
					StatusResult result = (StatusResult) clazz.newInstance();
					result.setStatus("error");
					result.setMessage("SC_FORBIDDEN");
					return (U) result;
				}
			}

			return parseJson(str, clazz);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Parses Json into type
	 * 
	 * @param str
	 *            Entity content
	 * @param clazz
	 *            Class
	 * @return Result
	 */
	public <U> U parseJson(String str, Class<U> clazz) {

		if (log.isInfoEnabled()) {

			if (log.isDebugEnabled()) {
				log.debug("Reading " + clazz.getSimpleName() + " from " + str);
			} else {
				String printStr = str;
				if (printStr.length() > 128) {
					printStr = printStr.substring(0, 128);
				}
				log.info("Reading " + clazz.getSimpleName() + " from " + printStr);
			}

		}

		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);

		try {
			return objectMapper.readValue(str, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Parses Json into type
	 * 
	 * @param is
	 *            Entity stream
	 * @param clazz
	 *            Class
	 * @return Result
	 */
	public T parseJson(InputStream is, Class<T> clazz) {
		try {
			return this.parseJson(IOUtils.toString(is), clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public JInstagram getApi() {
		return api;
	}

	public void setApi(JInstagram api) {
		this.api = api;
	}

}
