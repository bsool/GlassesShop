package com.bsool.glasses.glassesshop.service;

import com.bsool.glasses.glassesshop.data.inventory.InventoryItemDto;
import com.bsool.glasses.glassesshop.data.inventory.InventoryItemMapper;
import com.bsool.glasses.glassesshop.data.inventory.InventoryItemType;
import com.bsool.glasses.glassesshop.data.inventory.InventoryItemTypeDto;
import com.bsool.glasses.glassesshop.data.product.lens.Lens;
import com.bsool.glasses.glassesshop.data.product.lens.LensDto;
import com.bsool.glasses.glassesshop.data.product.lens.LensMapper;
import com.bsool.glasses.glassesshop.data.product.lens.LensRepository;
import com.bsool.glasses.glassesshop.data.product.lens.LensType;
import com.bsool.glasses.glassesshop.data.product.lens.LensTypeRepository;
import com.bsool.glasses.glassesshop.data.product.lens.PrescriptionType;
import com.bsool.glasses.glassesshop.data.product.lens.PrescriptionTypeRepository;
import com.bsool.glasses.glassesshop.exception.GlassesShopException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LensService implements UserClientService {


    private final LensRepository lensRepository;
    private final LensMapper lensMapper;
    private final InventoryService inventoryService;
    private final InventoryItemMapper inventoryItemMapper;
    private final PrescriptionTypeRepository prescriptionTypeRepository;
    private final LensTypeRepository lensTypeRepository;

    public LensDto updateLens(LensDto lensDto) {
        final Lens lens = lensMapper.toEntity(lensDto);

        if (lensDto.getInventoryItem() == null) {
            throw new GlassesShopException("no inventory item included");
        } else if (lensDto.getInventoryItem().getId() != null) {
            LensDto originalEntry = this.findById(lensDto.getId());
            if (!originalEntry.getInventoryItem().getId().equals(lensDto.getInventoryItem().getId())) {
                throw new GlassesShopException("inventoryItem.Id does not match original entry");
            } else if (lensDto.getInventoryItem().getInventoryItemType() == null ||
                    (originalEntry.getInventoryItem().getInventoryItemType().getTypeCode() != lensDto.getInventoryItem().getInventoryItemType().getTypeCode())) {
                throw new GlassesShopException("inventoryItem.InventoryItemType.typeCode does not match original entry");
            }
            lens.setInventoryItem(inventoryItemMapper.toEntity(inventoryService.getInventoryItemById(lensDto.getInventoryItem().getId())));
        } else {
            final InventoryItemTypeDto inventoryItemTypeDto = new InventoryItemTypeDto();
            inventoryItemTypeDto.setTypeCode(InventoryItemType.TYPE_CODE_LENS);
            lensDto.getInventoryItem().setInventoryItemType(inventoryItemTypeDto);
            lens.setInventoryItem(inventoryItemMapper.toEntity(inventoryService.updateItem(lensDto.getInventoryItem())));
        }

        if (lensDto.getPrescriptionType() == null) {
            throw new GlassesShopException("prescription type cannot be empty");
        } else {
            lens.setPrescriptionType(findByPrescriptionTypeCode(lensDto.getPrescriptionType().getTypeCode()));
        }

        if (lensDto.getLensType() == null) {
            throw new GlassesShopException("lens type cannot be empty");
        } else {
            lens.setLensType(findByLensTypeCode(lensDto.getPrescriptionType().getTypeCode()));
        }
        return lensMapper.toDto(lensRepository.save(lens));
    }

    private PrescriptionType findByPrescriptionTypeCode(final int typeCode) {
        return prescriptionTypeRepository.findByTypeCode(typeCode).orElseThrow(() -> new GlassesShopException("no prescription type found for code" + typeCode));
    }

    private LensType findByLensTypeCode(final int typeCode) {
        return lensTypeRepository.findByTypeCode(typeCode).orElseThrow(() -> new GlassesShopException("no prescription type found for code" + typeCode));
    }

    @Override
    public int deduct(long id) {
        final LensDto frame = findById(id);
        InventoryItemDto inventoryItem = frame.getInventoryItem();
        if (inventoryItem.getStock() < 1) {
            throw new GlassesShopException("not enough lenses in stock");
        }
        inventoryItem.setStock(inventoryItem.getStock() - 1);
        return inventoryService.updateItem(inventoryItem).getStock();
    }

    @Override
    public LensDto findById(long id) {
        return lensMapper.toDto(lensRepository.findById(id).orElseThrow(() -> new GlassesShopException("no lens found for id " + id)));

    }

    @Override
    public List<LensDto> getAvailableProducts() {
        return lensRepository.findAll().stream().map(lensMapper::toDto).collect(Collectors.toList());
    }
}
