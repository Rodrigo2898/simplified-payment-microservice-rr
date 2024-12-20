package com.rr.ms.user.api;

import com.rr.ms.user.api.dto.in.CreateUser;
import com.rr.ms.user.api.dto.in.UpdateUser;
import com.rr.ms.user.api.dto.out.UserResponse;
import com.rr.ms.user.domain.UserDomain;
import com.rr.ms.user.domain.UserService;

import java.util.List;

public class UserApi {

    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    public void create(CreateUser dto) {
        userService.save(dto.toDomain());
    }

    public UserResponse update(Long id, UpdateUser dto) {
        userService.save(dto.toDomain(id));
        UserDomain user = userService.findById(id);
        return UserResponse.fromDomain(user);
    }

    public List<UserResponse> list() {
        return userService.findAll().stream().map(UserResponse::fromDomain).toList();
    }
}
