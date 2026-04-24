package com.exbrotas.bag.dtos.security;

public record SystemUser(
    Integer id,
    String nome,
    String email,
    Boolean isAdmin
) {
}