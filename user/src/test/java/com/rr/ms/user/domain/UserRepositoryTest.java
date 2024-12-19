package com.rr.ms.user.domain;

import com.rr.ms.user.domain.enums.UserType;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Test
    void findAll() {
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

    @Test
    void findByName() {
        UserDomain userDomain1 = Instancio.of(UserDomain.class)
                .set(Select.field("cpf"), "338.061.710-50")
                .set(Select.field("email"), "test@example.com").create();
        UserDomain userDomain2 = Instancio.of(UserDomain.class)
                .set(Select.field("lastName"), "Feitosa")
                .set(Select.field("cpf"), "123.456.789-09")
                .set(Select.field("email"), "test2@example.com")
                .create();

        UserQuery query = new UserQuery.Builder().name("FEIT").build();

        userRepository().save(List.of(userDomain1, userDomain2));

        List<UserDomain> result = userRepository().find(query);

        assertEquals(1, result.size());
        assertEquals(userDomain2, result.get(0));
    }

    @Test
    void findByCpf() {
        UserDomain userDomain1 = Instancio.of(UserDomain.class)
                .set(Select.field("cpf"), "338.061.710-50")
                .set(Select.field("email"), "test@example.com").create();
        UserDomain userDomain2 = Instancio.of(UserDomain.class)
                .set(Select.field("cpf"), "123.456.789-09")
                .set(Select.field("email"), "test2@example.com")
                .create();

        UserQuery query = new UserQuery.Builder().cpf("123.456.789-09").build();

        userRepository().save(List.of(userDomain1, userDomain2));

        List<UserDomain> result = userRepository().find(query);

        assertEquals(1, result.size());
        assertEquals(userDomain2, result.getFirst());
    }
}
