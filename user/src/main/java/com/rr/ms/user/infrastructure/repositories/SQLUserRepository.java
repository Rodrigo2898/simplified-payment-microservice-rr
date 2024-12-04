package com.rr.ms.user.infrastructure.repositories;

import com.rr.ms.user.domain.UserDomain;
import com.rr.ms.user.domain.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class SQLUserRepository implements UserRepository {
    @Override
    public void save(List<UserDomain> users) {

    }

    @Override
    public List<UserDomain> findAll() {
        return List.of();
    }
}
