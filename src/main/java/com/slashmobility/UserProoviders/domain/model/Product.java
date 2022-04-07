package com.slashmobility.UserProoviders.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Data
public class Product {

    private Long id;

    private String name;

    private String type;

    private String description;

    private String image;

    private String providerName;

}
