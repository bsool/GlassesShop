package com.bsool.glasses.glassesshop.data.common;

import com.bsool.glasses.glassesshop.data.inventory.InventoryItemType;
import com.bsool.glasses.glassesshop.data.inventory.InventoryItemTypeDto;
import com.bsool.glasses.glassesshop.data.product.lens.LensType;
import com.bsool.glasses.glassesshop.data.product.lens.LensTypeDto;
import com.bsool.glasses.glassesshop.data.product.lens.PrescriptionType;
import com.bsool.glasses.glassesshop.data.product.lens.PrescriptionTypeDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TypeCodeMapper {
    TypeCode toEntity(TypeCodeDto typeCodeDto);

    TypeCodeDto toDto(TypeCode typeCode);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TypeCode partialUpdate(TypeCodeDto typeCodeDto, @MappingTarget TypeCode typeCode);

    InventoryItemType toEntity(InventoryItemTypeDto inventoryItemTypeDto);

    InventoryItemTypeDto toDto(InventoryItemType inventoryItemType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InventoryItemType partialUpdate(InventoryItemTypeDto inventoryItemTypeDto, @MappingTarget InventoryItemType inventoryItemType);

    LensType toEntity(LensTypeDto lensTypeDto);

    LensTypeDto toDto(LensType lensType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LensType partialUpdate(LensTypeDto lensTypeDto, @MappingTarget LensType lensType);

    PrescriptionType toEntity(PrescriptionTypeDto prescriptionTypeDto);

    PrescriptionTypeDto toDto(PrescriptionType prescriptionType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PrescriptionType partialUpdate(PrescriptionTypeDto prescriptionTypeDto, @MappingTarget PrescriptionType prescriptionType);
}