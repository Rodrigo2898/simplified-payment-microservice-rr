package com.rr.ms.user.factory;

import com.rr.ms.user.domain.UserDomain;
import com.rr.ms.user.domain.enums.UserType;

import java.math.BigDecimal;
import java.util.Optional;

public class CreateUserDomainFactory {

    public static UserDomain createUserDomain() {
        return new UserDomain(
                1L,
                "Son",
                "Goku",
                "859.077.630-19",
                "goku@email.com",
                BigDecimal.valueOf(220.50),
                Optional.of("9999999"),
                UserType.COMMON
        );
    }
}
