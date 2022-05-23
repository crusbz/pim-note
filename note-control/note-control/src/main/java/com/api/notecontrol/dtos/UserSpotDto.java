package com.api.notecontrol.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserSpotDto {

    @NotBlank
    @Size(max = 20)
    private String name_user;
    @NotBlank
    @Size(max = 10)
    private String password_user;
    @NotBlank
    @Size(max = 50)
    private String email_user;

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(String password_user) {
        this.password_user = password_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }
}
