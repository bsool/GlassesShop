package com.bsool.glasses.glassesshop.data.product.lens;

import com.bsool.glasses.glassesshop.data.common.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Lens}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LensDto extends ProductDto implements Serializable {
    private Long id;
    private String colour;
    private LensTypeDto lensType;
    private PrescriptionTypeDto prescriptionType;
}