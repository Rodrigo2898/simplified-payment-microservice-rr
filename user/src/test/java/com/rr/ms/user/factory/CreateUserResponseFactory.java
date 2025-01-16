package com.rr.ms.user.factory;

import com.rr.ms.user.api.dto.out.UserResponse;
import com.rr.ms.user.domain.enums.UserType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class CreateUserResponseFactory {

    public static List<UserResponse> createUserResponseList() {
        var user1 = new UserResponse(
                new Random().nextLong(),
                 "Rodrigo Feitosa",
                "859.077.630-19",
                "rodrigo@gmail.com",
                BigDecimal.valueOf(220.50),
                Optional.of("99999999"),
                UserType.COMMON
        );

        var user2 = new UserResponse(
                new Random().nextLong(),
                "Son Goku",
                "256.783.070-70",
                "goku@gmail.com",
                BigDecimal.valueOf(210.25),
                Optional.of("99999998"),
                UserType.COMMON
        );
        return List.of(user1, user2);
    }
}
