package com.rr.ms.user.domain;

import com.rr.ms.user.domain.enums.UserType;

import java.math.BigDecimal;
import java.util.Optional;

public record UserDomain(Long id,
                         String firstName,
                         String lastName,
                         String email,
                         BigDecimal balance,
                         String cpf,
                         Optional<String> phone,
                         UserType userType) {
}
