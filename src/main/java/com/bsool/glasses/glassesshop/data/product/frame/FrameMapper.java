package com.bsool.glasses.glassesshop.data.product.frame;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface FrameMapper {
    Frame toEntity(FrameDto frameDto);

    FrameDto toDto(Frame frame);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Frame partialUpdate(FrameDto frameDto, @MappingTarget Frame frame);
}