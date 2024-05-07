package com.bsool.glasses.glassesshop.data.product.lens;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface LensMapper {
    Lens toEntity(LensDto lensDto);

    LensDto toDto(Lens lens);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Lens partialUpdate(LensDto lensDto, @MappingTarget Lens lens);
}