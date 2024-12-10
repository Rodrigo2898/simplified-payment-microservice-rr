package com.rr.ms.user.infrastructure.utils;

import com.rr.ms.user.infrastructure.repositories.entities.enums.UserType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserTypeConverter implements AttributeConverter<UserType, String> {
    @Override
    public String convertToDatabaseColumn(UserType userType) {
        return userType == null ? null : userType.name();
    }

    @Override
    public UserType convertToEntityAttribute(String dbData) {
        return dbData == null ? null : UserType.valueOf(dbData);
    }
}
