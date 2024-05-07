package com.bsool.glasses.glassesshop.resource;

import com.bsool.glasses.glassesshop.data.product.frame.FrameDto;
import com.bsool.glasses.glassesshop.data.product.lens.LensDto;
import com.bsool.glasses.glassesshop.service.FrameService;
import com.bsool.glasses.glassesshop.service.LensService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("/rest/inventory")
@RequiredArgsConstructor
public class InventoryResource {

    private final FrameService frameService;
    private final LensService lensService;


    @PostMapping("/frame")
    public ResponseEntity<FrameDto> updateFrame(@RequestBody FrameDto frameDto) {
        return ResponseEntity.ok(frameService.updateFrame(frameDto));
    }

    @PostMapping("/lens")
    public ResponseEntity<LensDto> updateLens(@RequestBody LensDto frameDto) {
        return ResponseEntity.ok(lensService.updateLens(frameDto));
    }
}
