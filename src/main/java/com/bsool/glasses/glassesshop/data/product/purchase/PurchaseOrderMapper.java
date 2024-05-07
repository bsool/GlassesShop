package com.bsool.glasses.glassesshop.data.product.purchase;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PurchaseOrderMapper {
    PurchaseOrder toEntity(PurchaseOrderDto purchaseOrderDto);

    PurchaseOrderDto toDto(PurchaseOrder purchaseOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PurchaseOrder partialUpdate(PurchaseOrderDto purchaseOrderDto, @MappingTarget PurchaseOrder purchaseOrder);
}