package com.rr.ms.user.domain;

import jakarta.inject.Inject;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    void saveUser() {
        UserDomain userDomain = Instancio.create(UserDomain.class);

        userService.save(userDomain);

        verify(userRepository).save(userDomain);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void findAll() {
        userService.findAll();
    }
}