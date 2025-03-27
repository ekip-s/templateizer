package ru.templateizer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomPrincipal {

    private final String userId;
    private final String username;
}
