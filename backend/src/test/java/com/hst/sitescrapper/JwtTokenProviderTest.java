package com.hst.sitescrapper;

import com.hst.sitescrapper.service.JwtTokenProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dlgusrb0808@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class JwtTokenProviderTest {
	private JwtTokenProvider jwtTokenProvider = new JwtTokenProvider("HELLo?");

	@Test
	public void createTokenTest() {
		Map<String, Object> user = new HashMap<>();
		user.put("id", "gusrb0808");
		user.put("password", "cjsrn1992!");
		user.put("name", "leehg");

		Long userId = 19239129391239123L;

		String token = jwtTokenProvider.createToken(userId, "authorizedUser");

		System.out.println("\r\n\n\n" + token + "\r\n\n\n");

		assertThat(userId, is(jwtTokenProvider.parseToken(token)));
	}

}
