package com.bsool.glasses.glassesshop.data.inventory;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface InventoryItemMapper {
    InventoryItem toEntity(InventoryItemDto inventoryItemDto);

    InventoryItemDto toDto(InventoryItem inventoryItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InventoryItem partialUpdate(InventoryItemDto inventoryItemDto, @MappingTarget InventoryItem inventoryItem);
}