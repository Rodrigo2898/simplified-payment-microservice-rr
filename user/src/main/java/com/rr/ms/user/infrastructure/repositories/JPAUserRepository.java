package com.rr.ms.user.infrastructure.repositories;

import com.rr.ms.user.domain.UserDomain;
import com.rr.ms.user.domain.UserQuery;
import com.rr.ms.user.domain.UserRepository;
import com.rr.ms.user.infrastructure.repositories.entities.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JPAUserRepository implements UserRepository {

    private final EntityManager entityManager;

    public JPAUserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(List<UserDomain> users) {
        users.stream()
                .map(UserEntity::fromDomain)
                .forEach(entityManager::persist);
    }

    @Override
    public List<UserDomain> find(UserQuery userQuery) {
        return List.of();
    }
}
