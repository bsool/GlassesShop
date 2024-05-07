package com.bsool.glasses.glassesshop.data.product.purchase;

import com.bsool.glasses.glassesshop.data.product.frame.FrameDto;
import com.bsool.glasses.glassesshop.data.product.lens.LensDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link PurchaseOrder}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDto implements Serializable {
    private Long id;
    private LensDto lens;
    private float lensCost;
    private FrameDto frame;
    private float frameCost;
    private LocalDateTime purchase_date;
    private float totalCost;
    @NotEmpty
    private String currencyCode;

}