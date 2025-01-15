package com.rr.ms.user.infrastructure.resources;

import com.rr.ms.user.api.UserApi;
import com.rr.ms.user.api.dto.in.CreateUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    private final UserApi userApi;

    public UserResource(final UserApi userApi) {
        this.userApi = userApi;
    }

    @PostMapping
    public ResponseEntity<CreateUser> createUser(@RequestBody CreateUser user) {
        userApi.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
