package com.rr.ms.user.infrastructure.repositories;

import com.rr.ms.user.domain.UserDomain;
import com.rr.ms.user.domain.UserQuery;
import com.rr.ms.user.domain.UserRepository;
import com.rr.ms.user.infrastructure.repositories.entities.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        userQuery.ids().ifPresent(ids -> predicates.add(root.get("id").in(ids)));

        // Filtra pelo campo `name`
        userQuery.name().ifPresent(name ->
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"))
        );

        // Filtra pelo campo `cpf`
        userQuery.cpf().ifPresent(cpf ->
                predicates.add(cb.equal(root.get("cpf"), cpf))
        );

        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[0]));
        }

        TypedQuery<UserEntity> result = entityManager.createQuery(cq);
        return result.getResultList().stream().map(UserEntity::toDomain).toList();
    }

    @Override
    public Optional<UserDomain> findById(Long id) {
        UserEntity user = entityManager.find(UserEntity.class, id);
        return Optional.ofNullable(user)
                .map(UserEntity::toDomain);
    }
}
