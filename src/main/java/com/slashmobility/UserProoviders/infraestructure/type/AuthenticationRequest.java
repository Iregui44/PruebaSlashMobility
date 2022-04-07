package com.slashmobility.UserProoviders.infraestructure.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class AuthenticationRequest {

    public String username;

    public String password;
}
