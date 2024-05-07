package com.bsool.glasses.glassesshop.data.product.frame;

import com.bsool.glasses.glassesshop.data.common.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Frame}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrameDto extends ProductDto implements Serializable {
    private Long id;
    private boolean active;
    private String name;
}