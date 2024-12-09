package com.rr.ms.user.infrastructure.repositories;

import com.rr.ms.user.domain.UserDomain;
import com.rr.ms.user.domain.UserQuery;
import com.rr.ms.user.domain.UserRepository;
import com.rr.ms.user.infrastructure.repositories.entities.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class JPAUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;


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

    @Override
    public Optional<UserDomain> findById(Long id) {
        UserEntity user = entityManager.find(UserEntity.class, id);
        return Optional.ofNullable(user)
                .map(UserEntity::toDomain);
    }
}
