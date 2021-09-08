package com.hst.sitescrapper.model.converter;

import com.hst.sitescrapper.type.UserStatusType;

import javax.persistence.AttributeConverter;

/**
 * @author dlgusrb0808@gmail.com
 */
public class UserStatusTypeConverter implements AttributeConverter<UserStatusType, Integer> {
	@Override
	public Integer convertToDatabaseColumn(UserStatusType userStatusType) {
		return userStatusType.getStatusCode();
	}

	@Override
	public UserStatusType convertToEntityAttribute(Integer statusCode) {
		return UserStatusType.get(statusCode);
	}
}
