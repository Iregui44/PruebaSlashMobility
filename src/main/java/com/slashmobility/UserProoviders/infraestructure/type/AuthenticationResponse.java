package com.slashmobility.UserProoviders.infraestructure.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class AuthenticationResponse {

    private String access_token;

    private String refresh_token;

    private String token_type;

    private Long expires;

    public AuthenticationResponse(String access_token) {
        this.access_token = access_token;
        this.token_type = "Bearer";
        this.expires = expires;
    }
}
