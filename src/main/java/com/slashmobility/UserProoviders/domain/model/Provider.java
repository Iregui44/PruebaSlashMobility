package com.slashmobility.UserProoviders.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Data
public class Provider {

    private String name;

    private String address;

    private String telephone;

    private String city;
}
