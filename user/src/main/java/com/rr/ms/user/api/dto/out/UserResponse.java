package com.rr.ms.user.api.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rr.ms.user.domain.UserDomain;
import com.rr.ms.user.domain.enums.UserType;

import java.math.BigDecimal;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record UserResponse(Long id,
                           String fullName,
                           String cpf,
                           String email,
                           BigDecimal balance,
                           Optional<String> phone,
                           UserType userType) {
    public static UserResponse fromDomain(UserDomain user) {
        return new UserResponse(
                user.id(),
                user.firstName() + " " + user.lastName(),
                user.cpf(),
                user.email(),
                user.balance(),
                user.phone(),
                user.userType()
        );
    }
}
