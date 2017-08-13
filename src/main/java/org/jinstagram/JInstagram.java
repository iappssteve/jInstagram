
package org.jinstagram;

import java.io.IOException;
import java.util.Optional;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.jinstagram.requests.InstagramLoginRequest;
import org.jinstagram.requests.InstagramRequest;
import org.jinstagram.requests.InstagramSyncFeaturesRequest;
import org.jinstagram.requests.internal.InstagramFetchHeadersRequest;
import org.jinstagram.requests.payload.InstagramLoginPayload;
import org.jinstagram.requests.payload.InstagramLoginResult;
import org.jinstagram.requests.payload.InstagramSyncFeaturesPayload;
import org.jinstagram.util.InstagramGenericUtil;
import org.jinstagram.util.InstagramHashUtil;

/**
 * 
 * JInstagram API
 *
 */
public class JInstagram {
	protected Logger log = Logger.getLogger(JInstagram.class);
	protected String deviceId;
	protected String uuid;
	protected String username;
	protected String password;
	protected long userId;
	protected String rankToken;
	protected boolean isLoggedIn;
	protected HttpResponse lastResponse;
	protected boolean debug;
	protected CloseableHttpClient client;
	protected CookieStore cookieStore;

	/**
	 * @param username
	 *            Username
	 * @param password
	 *            Password
	 */
	public JInstagram(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * Setup some variables
	 */
	public void setup() {
		log.info("Setup...");

		if (this.username == null || this.username.trim().isEmpty()) {
			throw new IllegalArgumentException("Username is mandatory.");
		}

		if (this.password == null || this.password.trim().isEmpty()) {
			throw new IllegalArgumentException("Password is mandatory.");
		}

		this.deviceId = InstagramHashUtil.generateDeviceId(this.username, this.password);
		this.uuid = InstagramGenericUtil.generateUuid(true);

		log.info("Device ID is: " + this.deviceId + ", random id: " + this.uuid);

		// Create global request configuration
		RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();

		this.cookieStore = new BasicCookieStore();
		this.client = HttpClients.custom().setDefaultCookieStore(this.cookieStore)
				.setDefaultRequestConfig(defaultRequestConfig).build();
	}

	/**
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public InstagramLoginResult login() throws JInstagramException {
		log.info("Logging with user " + username + " and password " + password.replaceAll("[a-zA-Z0-9]", "*"));

		InstagramLoginPayload loginRequest = InstagramLoginPayload.builder().username(username).password(password)
				.guid(uuid).device_id(deviceId).phone_id(InstagramGenericUtil.generateUuid(true))
				.login_attempt_account(0)._csrftoken(getOrFetchCsrf()).build();

		InstagramLoginResult loginResult = this.sendRequest(new InstagramLoginRequest(loginRequest));
		if (loginResult.getStatus().equalsIgnoreCase("ok")) {
			this.userId = loginResult.getLogged_in_user().getPk();
			this.rankToken = this.userId + "_" + this.uuid;
			this.isLoggedIn = true;

			InstagramSyncFeaturesPayload syncFeatures = InstagramSyncFeaturesPayload.builder()._uuid(uuid)
					._csrftoken(getOrFetchCsrf())._uid(userId).id(userId)
					.experiments(InstagramConstants.DEVICE_EXPERIMENTS).build();

			this.sendRequest(new InstagramSyncFeaturesRequest(syncFeatures));
			// this.sendRequest(new InstagramAutoCompleteUserListRequest());
			// this.sendRequest(new InstagramTimelineFeedRequest());
			// this.sendRequest(new InstagramGetInboxRequest());
			// this.sendRequest(new InstagramGetRecentActivityRequest());
		}
		
		return loginResult;
	}

	/**
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String getOrFetchCsrf() throws JInstagramException {
		Optional<Cookie> checkCookie = getCsrfCookie();
		if (!checkCookie.isPresent()) {
			sendRequest(new InstagramFetchHeadersRequest());
			checkCookie = getCsrfCookie();
		}
		String csrfToken = checkCookie.get().getValue();
		return csrfToken;
	}

	public Optional<Cookie> getCsrfCookie() {
		return this.cookieStore.getCookies().stream().filter(cookie -> cookie.getName().equalsIgnoreCase("csrftoken"))
				.findFirst();
	}

	/**
	 * Send request to endpoint
	 * 
	 * @param request
	 *            Request object
	 * @return success flag
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public <T> T sendRequest(InstagramRequest<T> request) throws JInstagramException {

		log.info("Sending request: " + request.getClass().getName());

		try {
			if (!this.isLoggedIn && request.requiresLogin()) {
				throw new IllegalStateException("Need to login first!");
			}

			request.setApi(this);
			T response = request.execute();

			log.debug("Result for " + request.getClass().getName() + ": " + response);

			return response;
		} catch (JInstagramException e) {
			String msg = String.format("Error %s sending request %s", e.getCause().getMessage(), request);
			log.error(msg);
			throw e;
		}
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getRankToken() {
		return rankToken;
	}

	public void setRankToken(String rankToken) {
		this.rankToken = rankToken;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public HttpResponse getLastResponse() {
		return lastResponse;
	}

	public void setLastResponse(HttpResponse lastResponse) {
		this.lastResponse = lastResponse;
	}

	public String getUuid() {
		return uuid;
	}

	public CloseableHttpClient getClient() {
		return client;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String deviceId;
		private String uuid;
		private String username;
		private String password;
		private long userId;

		public Builder deviceId(String deviceId) {
			this.deviceId = deviceId;
			return this;
		}

		public Builder uuid(String uuid) {
			this.uuid = uuid;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder userId(long userId) {
			this.userId = userId;
			return this;
		}

		public JInstagram build() {
			return new JInstagram(this);
		}
	}

	private JInstagram(Builder builder) {
		this.deviceId = builder.deviceId;
		this.uuid = builder.uuid;
		this.username = builder.username;
		this.password = builder.password;
		this.userId = builder.userId;
	}
}