package com.bsool.glasses.glassesshop.resource;

import com.bsool.glasses.glassesshop.data.product.frame.FrameDto;
import com.bsool.glasses.glassesshop.data.product.lens.LensDto;
import com.bsool.glasses.glassesshop.data.product.purchase.PurchaseOrderDto;
import com.bsool.glasses.glassesshop.service.FrameService;
import com.bsool.glasses.glassesshop.service.LensService;
import com.bsool.glasses.glassesshop.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("hasAnyAuthority('USER')")
@RequestMapping("/rest/product")
@RequiredArgsConstructor
public class ProductResource {

    private final FrameService frameService;
    private final LensService lensService;
    private final PurchaseService purchaseService;

    @GetMapping("/frame")
    public ResponseEntity<List<FrameDto>> getActiveFrames() {
        return ResponseEntity.ok(frameService.getAvailableProducts());
    }

    @GetMapping("/lens")
    public ResponseEntity<List<LensDto>> getLenses() {
        return ResponseEntity.ok(lensService.getAvailableProducts());
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseOrderDto> purchaseGlasses(@Valid @RequestBody PurchaseOrderDto purchaseOrderDto) {
        return ResponseEntity.ok(purchaseService.makePurchaseOrder(purchaseOrderDto));
    }

}
