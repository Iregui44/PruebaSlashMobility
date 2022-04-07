package com.slashmobility.UserProoviders.infraestructure.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class UserUpdateRequest {

    private String email;

    private String name;

    private String lastName;

}
