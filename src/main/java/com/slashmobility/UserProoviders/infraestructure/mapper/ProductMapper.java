package com.slashmobility.UserProoviders.infraestructure.mapper;

import com.slashmobility.UserProoviders.domain.model.Product;
import com.slashmobility.UserProoviders.infraestructure.adapter.persistence.entity.ProductEntity;
import com.slashmobility.UserProoviders.infraestructure.type.ProductUpdateRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    public Product toDomain(ProductEntity productEntity);

    public ProductEntity toEntity(Product product);

    public List<Product> toDomains(List<ProductEntity> products);

    public Product toDomainOfUpdateRequest(ProductUpdateRequest productUpdateRequest);
}
