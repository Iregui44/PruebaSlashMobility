package com.slashmobility.UserProoviders.infraestructure.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class DefaultResponse {

    private LocalDateTime time;

    private String message;

    public DefaultResponse(String message) {
        this.message =  message;
        this.time = LocalDateTime.now();
    }
}
