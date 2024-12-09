package com.rr.ms.user.domain;

import com.rr.ms.user.infrastructure.repositories.entities.UserEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
public abstract class UserRepositoryTest {

    public abstract UserRepository userRepository();

    @Test
    void save() {
        UserDomain userDomain = Instancio.create(UserDomain.class);
        userRepository().save(userDomain);

        Optional<UserDomain> result = userRepository().findById(userDomain.id());

        assertTrue(result.isPresent());
        assertEquals(userDomain, result.get());
    }
}
