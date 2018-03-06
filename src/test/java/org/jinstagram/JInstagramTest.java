package org.jinstagram;

import static org.junit.Assert.*;

import org.jinstagram.requests.payload.InstagramLoginResult;
import org.junit.Test;

public class JInstagramTest {

	@Test
	public void testLogin() {
		JInstagram instagram = JInstagram.builder().username("steve@iappqweqwasdas.cc").password("afasdfser").build();
		instagram.setup();
		try {
			InstagramLoginResult loginResults = instagram.login();
			if (loginResults != null) {
				assertTrue(loginResults.getStatus().equals("fail"));
			}
		} catch (JInstagramException e) {
			fail("Authentication call failed");
		}
	}

}
