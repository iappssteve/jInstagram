
package org.jinstagram.requests.payload;

import java.util.List;

/**
 * Search Tag Result
 */
public class InstagramSearchTagsResult extends StatusResult {
	private List<InstagramSearchTagsResultTag> results;
	private boolean has_more;
	private int num_results;

	public List<InstagramSearchTagsResultTag> getResults() {
		return results;
	}

	public void setResults(List<InstagramSearchTagsResultTag> results) {
		this.results = results;
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
