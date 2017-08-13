
package org.jinstagram.requests.payload;

/**
 * Sync Features Payload
 */
public class InstagramSyncFeaturesPayload {
	private String _uuid;
	private long _uid;
	private long id;
	private String _csrftoken;
	private String experiments;

	public String get_uuid() {
		return _uuid;
	}

	public void set_uuid(String _uuid) {
		this._uuid = _uuid;
	}

	public long get_uid() {
		return _uid;
	}

	public void set_uid(long _uid) {
		this._uid = _uid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String get_csrftoken() {
		return _csrftoken;
	}

	public void set_csrftoken(String _csrftoken) {
		this._csrftoken = _csrftoken;
	}

	public String getExperiments() {
		return experiments;
	}

	public void setExperiments(String experiments) {
		this.experiments = experiments;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String _uuid;
		private long _uid;
		private long id;
		private String _csrftoken;
		private String experiments;

		public Builder _uuid(String _uuid) {
			this._uuid = _uuid;
			return this;
		}

		public Builder _uid(long _uid) {
			this._uid = _uid;
			return this;
		}

		public Builder id(long id) {
			this.id = id;
			return this;
		}

		public Builder _csrftoken(String _csrftoken) {
			this._csrftoken = _csrftoken;
			return this;
		}

		public Builder experiments(String experiments) {
			this.experiments = experiments;
			return this;
		}

		public InstagramSyncFeaturesPayload build() {
			return new InstagramSyncFeaturesPayload(this);
		}
	}

	private InstagramSyncFeaturesPayload(Builder builder) {
		this._uuid = builder._uuid;
		this._uid = builder._uid;
		this.id = builder.id;
		this._csrftoken = builder._csrftoken;
		this.experiments = builder.experiments;
	}
}