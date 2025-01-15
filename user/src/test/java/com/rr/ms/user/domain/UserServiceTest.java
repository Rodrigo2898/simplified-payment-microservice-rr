package com.rr.ms.user.domain;

import jakarta.inject.Inject;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
        List<UserDomain> users = Instancio.stream(UserDomain.class).limit(10).toList();

        when(userRepository.findAll()).thenReturn(users);

        List<UserDomain> result = userService.findAll();

        verify(userRepository).findAll();
        verifyNoMoreInteractions(userRepository);

        assertEquals(users, result);
    }

    @Test
    void findById_whenUserIsFound_returnsCandidate() {
        UserDomain userDomain = Instancio.create(UserDomain.class);

        when(userRepository.findById(userDomain.id())).thenReturn(Optional.of(userDomain));

        UserDomain result = userService.findById(userDomain.id());

        verify(userRepository).findById(userDomain.id());
        verifyNoMoreInteractions(userRepository);

        assertEquals(userDomain, result);
    }

    @Test
    void findById_whenUserIsNotFound_throwsException() {
        UserDomain userDomain = Instancio.create(UserDomain.class);

        when(userRepository.findById(userDomain.id())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.findById(userDomain.id()));

        verify(userRepository).findById(userDomain.id());
        verifyNoMoreInteractions(userRepository);
    }
}