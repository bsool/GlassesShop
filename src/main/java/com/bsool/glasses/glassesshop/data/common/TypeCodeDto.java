package com.bsool.glasses.glassesshop.data.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link TypeCode}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeCodeDto implements Serializable {
    private Long id;
    private int typeCode;
    private String typeName;
}