package com.rr.ms.user.domain;

import com.rr.ms.user.domain.enums.UserType;
import com.rr.ms.user.infrastructure.repositories.entities.UserEntity;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    @Test
    void findAll() {
        // Gerando uma lista de 10 usuários com valores válidos para 'userType'
        List<UserDomain> users = Instancio.ofList(UserDomain.class).size(12)
                .supply(Select.field(UserDomain.class, "cpf"), () -> "123.456.789-09")
                .supply(Select.field(UserDomain.class, "email"), () -> "test@example.com")
                // Gerando um valor válido para o campo 'userType' usando enum
                .generate(Select.field(UserDomain.class, "userType"), gen -> gen.enumOf(UserType.class))
                .create();

        userRepository().save(users);

        List<UserDomain> result = userRepository().findAll();

        assertEquals(users.size(), result.size(), "O número de usuários salvos deve coincidir com o recuperado");
    }
}
