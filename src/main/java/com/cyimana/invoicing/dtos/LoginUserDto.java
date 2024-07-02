package com.cyimana.invoicing.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginUserDto {
    private String email;
    private String password;

    public LoginUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public LoginUserDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
