package com.slashmobility.UserProoviders.infraestructure.adapter.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="providers")
public class ProviderEntity {

    @Id
    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="telephone")
    private String telephone;

    @Column(name="city")
    private String city;

}
