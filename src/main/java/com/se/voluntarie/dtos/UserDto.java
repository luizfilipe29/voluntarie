package com.se.voluntarie.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserDto(@NotBlank String email, @NotBlank String name) {
}
