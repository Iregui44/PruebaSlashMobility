package com.slashmobility.UserProoviders.infraestructure.adapter.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="type")
    private String type;

    @Column(name="description")
    private String description;

    @Column(name="image")
    private String image;

    @Column(name="provider_name")
    private String providerName;

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name="provider_name", referencedColumnName="name", insertable = false, updatable = false)
    })
    private ProviderEntity provider;


}
