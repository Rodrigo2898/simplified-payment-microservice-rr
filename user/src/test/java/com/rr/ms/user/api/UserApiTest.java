package com.rr.ms.user.api;

import com.rr.ms.user.api.dto.in.CreateUser;
import com.rr.ms.user.api.dto.in.UpdateUser;
import com.rr.ms.user.api.dto.out.UserResponse;
import com.rr.ms.user.domain.UserDomain;
import com.rr.ms.user.domain.UserService;
import jakarta.inject.Inject;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserApiTest {
    @Mock
    private UserService userService;  // Mock the UserService

    @InjectMocks
    private UserApi userApi;

    @Test
    void createUser() {
        CreateUser dto = Instancio.create(CreateUser.class);

        ArgumentCaptor<UserDomain> captor = ArgumentCaptor.forClass(UserDomain.class);

        userApi.create(dto);

        verify(userService).save(captor.capture());
        verifyNoMoreInteractions(userService);

        UserDomain user = captor.getValue();

        assertEquals(user.firstName(), dto.firstName());
        assertEquals(user.lastName(), dto.lastName());
        assertEquals(user.email(), dto.email());
        assertEquals(user.phone(), dto.phone());
        assertEquals(user.balance(), dto.balance());
        assertEquals(user.cpf(), dto.cpf());
        assertEquals(user.userType(), dto.userType());
    }

    @Test
    void updateUser() {
        Long id = new Random().nextLong();
        UpdateUser dto = Instancio.create(UpdateUser.class);
        UserDomain user = dto.toDomain(id);

        ArgumentCaptor<UserDomain> captor = ArgumentCaptor.forClass(UserDomain.class);

        when(userService.findById(id)).thenReturn(user);

        var response = userApi.update(id, dto);

        verify(userService).save(captor.capture());
        verify(userService).findById(id);
        verifyNoMoreInteractions(userService);

        assertEquals(UserResponse.fromDomain(user), response);
    }


    @Test
    void listUser() {
        var users = Instancio.stream(UserDomain.class).limit(10).toList();

        when(userService.findAll()).thenReturn(users);

        var response = userApi.list();

        assertNotNull(response);

        verify(userService).findAll();
        verifyNoMoreInteractions(userService);

        assertEquals(users.stream().map(UserResponse::fromDomain).toList(), response);
    }

}