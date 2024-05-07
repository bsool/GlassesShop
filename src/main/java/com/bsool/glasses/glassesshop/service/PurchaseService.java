package com.bsool.glasses.glassesshop.service;

import com.bsool.glasses.glassesshop.data.inventory.InventoryItemDto;
import com.bsool.glasses.glassesshop.data.product.frame.FrameDto;
import com.bsool.glasses.glassesshop.data.product.frame.FrameMapper;
import com.bsool.glasses.glassesshop.data.product.lens.LensDto;
import com.bsool.glasses.glassesshop.data.product.lens.LensMapper;
import com.bsool.glasses.glassesshop.data.product.purchase.CurrencyType;
import com.bsool.glasses.glassesshop.data.product.purchase.CurrencyTypeRepository;
import com.bsool.glasses.glassesshop.data.product.purchase.PurchaseOrder;
import com.bsool.glasses.glassesshop.data.product.purchase.PurchaseOrderDto;
import com.bsool.glasses.glassesshop.data.product.purchase.PurchaseOrderMapper;
import com.bsool.glasses.glassesshop.data.product.purchase.PurchaseOrderRepository;
import com.bsool.glasses.glassesshop.exception.GlassesShopException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final CurrencyTypeRepository currencyTypeRepository;
    private final LensService lensService;
    private final FrameService frameService;
    private final LensMapper lensMapper;
    private final FrameMapper frameMapper;
    private final PurchaseOrderMapper purchaseOrderMapper;

    @Transactional
    public PurchaseOrderDto makePurchaseOrder(final PurchaseOrderDto purchaseOrderDto) {
        final PurchaseOrder purchaseOrder = new PurchaseOrder();
        final CurrencyType currencyType = currencyTypeRepository.findByTypeNameIgnoreCase(purchaseOrderDto.getCurrencyCode())
                .orElseThrow(() -> new GlassesShopException("currency code note supported"));
        purchaseOrder.setCurrencyCode(currencyType.getTypeName());
        final FrameDto frameDto = frameService.findById(purchaseOrderDto.getFrame().getId());
        if (!frameDto.isActive()) {
            throw new GlassesShopException("frame is not currently active");
        }
        purchaseOrder.setFrame(frameMapper.toEntity(frameDto));
        purchaseOrder.setFrameCost(getCostInCurrencyCode(frameDto.getInventoryItem(), currencyType));
        frameService.deduct(frameDto.getId());

        final LensDto lensDto = lensService.findById(purchaseOrderDto.getLens().getId());
        purchaseOrder.setLens(lensMapper.toEntity(lensDto));
        purchaseOrder.setLensCost(getCostInCurrencyCode(lensDto.getInventoryItem(), currencyType));
        lensService.deduct(lensDto.getId());
        return purchaseOrderMapper.toDto(purchaseOrderRepository.save(purchaseOrder));
    }

    private float getCostInCurrencyCode(final InventoryItemDto inventoryItemDto, final CurrencyType currencyType) {
        switch (currencyType.getTypeCode()) {
            case 1:
                return inventoryItemDto.getPriceUSD();
            case 2:
                return inventoryItemDto.getPriceGBP();
            case 3:
                return inventoryItemDto.getPriceEUR();
            case 4:
                return inventoryItemDto.getPriceJOD();
            case 5:
                return inventoryItemDto.getPriceJPY();
            default:
        }
        throw new GlassesShopException("currency code note supported");
    }
}
