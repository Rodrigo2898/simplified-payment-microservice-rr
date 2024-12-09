package com.rr.ms.user.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository {
    void save(List<UserDomain> users);

    default void save(UserDomain user) {
        save(List.of(user));
    }

    List<UserDomain> find(UserQuery userQuery);

    default List<UserDomain> findAll() {
        return find(new UserQuery.Builder().build());
    }

    Optional<UserDomain> findById(Long id);
}
