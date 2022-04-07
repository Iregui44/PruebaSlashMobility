package com.slashmobility.UserProoviders.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Data
public class User {

    private String userName;

    private String password;

    private String email;

    private String name;

    private String lastName;

    private boolean isEmailConfirm;

}
