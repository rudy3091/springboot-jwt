package com.rudy.jwtloginexample.domain.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("ROLE_ADMIN", "Server admin"),
    USER("ROLE_USER", "General user");

    private final String key;
    private final String description;
}
