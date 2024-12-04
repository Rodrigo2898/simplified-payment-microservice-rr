package com.rr.ms.user.domain;

import java.util.List;

public interface UserRepository {
    void save(List<UserDomain> users);

    default void save(UserDomain user) {
        save(List.of(user));
    }

    List<UserDomain> findAll();
}
