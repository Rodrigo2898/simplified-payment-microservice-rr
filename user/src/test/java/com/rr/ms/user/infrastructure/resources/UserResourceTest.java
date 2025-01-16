package com.rr.ms.user.infrastructure.resources;

import com.rr.ms.user.api.UserApi;
import com.rr.ms.user.api.dto.in.CreateUser;
import com.rr.ms.user.domain.UserDomain;
import com.rr.ms.user.factory.CreateUserDomainFactory;
import com.rr.ms.user.factory.CreateUserFactory;
import com.rr.ms.user.factory.CreateUserResponseFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserResourceTest {

    @Mock
    UserApi userApi;

    @InjectMocks
    UserResource userResource;


    @Test
    void createUser() {
        // ARRANGE
        var dto = CreateUserFactory.build();

        ArgumentCaptor<CreateUser> dtoCapture = ArgumentCaptor.forClass(CreateUser.class);

        // ACT
        var response = userResource.createUser(dto);

        // ASSERT
        assertNotNull(response);
        assertNotNull(response.getBody());

        verify(userApi).create(dtoCapture.capture());
        verifyNoMoreInteractions(userApi);

        UserDomain userDomain = dtoCapture.getValue().toDomain();

        assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());

        assertEquals(userDomain.firstName(), dtoCapture.getValue().firstName());
        assertEquals(userDomain.lastName(), dtoCapture.getValue().lastName());
        assertEquals(userDomain.email(), dtoCapture.getValue().email());
        assertEquals(userDomain.phone(), dtoCapture.getValue().phone());
        assertEquals(userDomain.balance(), dtoCapture.getValue().balance());
        assertEquals(userDomain.cpf(), dtoCapture.getValue().cpf());
    }

    @Test
    void listAllUsers() {
        // ARRANGE
        var users = CreateUserResponseFactory.createUserResponseList();

        when(userApi.list()).thenReturn(users);

        // ACT
        var response = userResource.listAll();

        // ASSERT
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(2, response.getBody().size());

        verify(userApi).list();
        verifyNoMoreInteractions(userApi);
    }
}