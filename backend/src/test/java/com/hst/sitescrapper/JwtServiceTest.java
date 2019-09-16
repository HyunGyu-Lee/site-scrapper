package com.hst.sitescrapper;

import com.hst.sitescrapper.service.JwtService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dlgusrb0808@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class JwtServiceTest {
	private JwtService jwtService = new JwtService("MY_SECRET_KEY!!!");

	@Test
	public void createTokenTest() {
		Map<String, Object> user = new HashMap<>();
		user.put("id", "gusrb0808");
		user.put("password", "cjsrn1992!");
		user.put("name", "leehg");

		String token = jwtService.createToken("user", user, "authorizedUser");

		System.out.println("\r\n\n\n" + token + "\r\n\n\n");

		jwtService.parseToken(token, "user", "authorizedUser", Map.class);
	}

}
