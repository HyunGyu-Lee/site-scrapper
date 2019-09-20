package com.hst.sitescrapper.service;

import com.hst.sitescrapper.constants.GlobalConstants;
import com.hst.sitescrapper.model.entity.UserEntity;
import com.hst.sitescrapper.model.exception.UnAuthorizedException;
import com.hst.sitescrapper.model.request.SigninRequest;
import com.hst.sitescrapper.model.request.SignupRequest;
import com.hst.sitescrapper.model.response.UserResponse;
import com.hst.sitescrapper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.hst.sitescrapper.model.exception.DataNotFoundException;
import java.util.Collections;
import java.util.Optional;

/**
 * @author dlgusrb0808@gmail.com
 */
@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider,
					   PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.jwtTokenProvider = jwtTokenProvider;
		this.passwordEncoder = passwordEncoder;
	}

	public UserEntity getUserByLoginId(String loginId) {
		Optional<UserEntity> userEntity = userRepository.findByLoginId(loginId);
		UserEntity user = userEntity.orElseThrow(()
				-> new DataNotFoundException(String.format("Not found user given loginId: %s", loginId)));
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		return userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new UsernameNotFoundException(userId));
	}

	/***
	 * JWT Token 으로 인증정보 획득
	 * @param token
	 * @return
	 */
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = loadUserByUsername(jwtTokenProvider.parseUserId(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	/***
	 * 사용자 인증
	 * @param signinRequest 인증 요청
	 * @return 인증된 토큰
	 */
	public String signin(SigninRequest signinRequest) {
		UserEntity user = getUserByLoginId(signinRequest.getLoginId());
		if (!passwordEncoder.matches(signinRequest.getLoginPassword(), user.getLoginPassword())) {
			throw new UnAuthorizedException("Password is incorrect");
		}
		return jwtTokenProvider.createToken(user.getId(), UserResponse.convert(user));
	}

	/***
	 * 신규 사용자 등록
	 * @param request
	 */
	public void signup(SignupRequest request) {
		UserEntity user = new UserEntity();
		user.setLoginId(request.getLoginId());
		user.setLoginPassword(passwordEncoder.encode(request.getLoginPassword()));
		user.setEmail(request.getEmail());
		user.setName(request.getName());
		user.setRoles(Collections.singleton(GlobalConstants.Roles.NORMAL_USER_ROLE));

		// TODO / 이메일 인증 발송

		userRepository.save(user);
	}
}
