package com.exbrotas.bag.dtos.security;

public record AuthUser(
    Integer id,
    String nome,
    String email,
    Boolean isAdmin
) {
}