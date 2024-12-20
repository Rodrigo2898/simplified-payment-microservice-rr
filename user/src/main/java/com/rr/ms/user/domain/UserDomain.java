package com.rr.ms.user.domain;

import com.rr.ms.user.domain.enums.UserType;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

public record UserDomain(Long id,
                         String firstName,
                         String lastName,
                         String cpf,
                         String email,
                         BigDecimal balance,
                         Optional<String> phone,
                         UserType userType) {
    public static UserDomain create(String firstName,
                                    String lastName,
                                    String cpf,
                                    String email,
                                    BigDecimal balance,
                                    Optional<String> phone,
                                    UserType userType) {
        return new UserDomain(new Random().nextLong(), firstName, lastName, cpf, email, balance, phone, userType);
    }
}
