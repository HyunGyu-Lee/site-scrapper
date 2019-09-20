package com.hst.sitescrapper.model.entity;

import com.hst.sitescrapper.model.converter.UserStatusTypeConverter;
import com.hst.sitescrapper.type.UserStatusType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author dlgusrb0808@gmail.com
 */
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity implements UserDetails {
	@Column(nullable = false, unique = true)
	private String loginId;

	@Column(nullable = false)
	private String loginPassword;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	@Enumerated
	@Convert(converter = UserStatusTypeConverter.class)
	private UserStatusType userStatusType = UserStatusType.EMAIL_NOT_VERIFIED;

	@Column
	private String profileImageUrl;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "role"))
	private Set<String> roles;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public UserStatusType getUserStatusType() {
		return userStatusType;
	}

	public void setUserStatusType(UserStatusType userStatusType) {
		this.userStatusType = userStatusType;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return this.loginPassword;
	}

	@Override
	public String getUsername() {
		return this.loginId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
