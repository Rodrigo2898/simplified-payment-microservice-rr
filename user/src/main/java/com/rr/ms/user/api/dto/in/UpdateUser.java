package com.rr.ms.user.api.dto.in;

import com.rr.ms.user.domain.UserDomain;
import com.rr.ms.user.domain.enums.UserType;

import java.math.BigDecimal;
import java.util.Optional;

public record UpdateUser (String firstName,
                          String lastName,
                          String cpf,
                          String email,
                          BigDecimal balance,
                          Optional<String> phone,
                          UserType userType) {
    public UserDomain toDomain(Long id) {
        return new UserDomain(id, firstName, lastName, cpf, email, balance, phone, userType);
    }
}
