package com.bsool.glasses.glassesshop.service;

import com.bsool.glasses.glassesshop.config.security.ApiAuthenticationService;
import com.bsool.glasses.glassesshop.data.inventory.InventoryItemDto;
import com.bsool.glasses.glassesshop.data.inventory.InventoryItemMapper;
import com.bsool.glasses.glassesshop.data.inventory.InventoryItemType;
import com.bsool.glasses.glassesshop.data.inventory.InventoryItemTypeDto;
import com.bsool.glasses.glassesshop.data.product.frame.Frame;
import com.bsool.glasses.glassesshop.data.product.frame.FrameDto;
import com.bsool.glasses.glassesshop.data.product.frame.FrameMapper;
import com.bsool.glasses.glassesshop.data.product.frame.FrameRepository;
import com.bsool.glasses.glassesshop.data.user.EntityUser;
import com.bsool.glasses.glassesshop.exception.GlassesShopException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FrameService implements UserClientService {

    private final FrameRepository frameRepository;
    private final FrameMapper frameMapper;
    private final InventoryService inventoryService;
    private final InventoryItemMapper inventoryItemMapper;
    private final ApiAuthenticationService apiAuthenticationService;

    public FrameDto updateFrame(final FrameDto frameDto) {
        if (frameDto.getId() == null) {
            return createNewFrame(frameDto);
        } else {
            final FrameDto originalEntry = this.findById(frameDto.getId());
            if (!originalEntry.getInventoryItem().getId().equals(frameDto.getInventoryItem().getId())) {
                throw new GlassesShopException("inventoryItem.Id does not match original entry");
            } else if (originalEntry.getInventoryItem().getInventoryItemType().getTypeCode() != frameDto.getInventoryItem().getInventoryItemType().getTypeCode()) {
                throw new GlassesShopException("inventoryItem.InventoryItemType.typeCode does not match original entry");
            }
        }

        final Frame frame = frameMapper.toEntity(frameDto);
        if (frameDto.getInventoryItem() == null || frameDto.getInventoryItem().getId() == null) {
            throw new GlassesShopException("inventory item Id not found");
        } else {
            frameDto.setInventoryItem(updateInventoryItem(frameDto.getInventoryItem()));
        }
        return frameMapper.toDto(frameRepository.save(frame));
    }

    public FrameDto createNewFrame(final FrameDto frameDto) {
        final Frame frame = frameMapper.toEntity(frameDto);
        if (frameDto.getInventoryItem() != null && frameDto.getInventoryItem().getId() != null) {
            throw new GlassesShopException("inventory item already mapped create a new inventory item");
        } else {
            InventoryItemTypeDto inventoryItemTypeDto = new InventoryItemTypeDto();
            inventoryItemTypeDto.setTypeCode(InventoryItemType.TYPE_CODE_FRAME);
            frameDto.getInventoryItem().setInventoryItemType(inventoryItemTypeDto);
            frame.setInventoryItem(inventoryItemMapper.toEntity(inventoryService.updateItem(frameDto.getInventoryItem())));
        }
        return frameMapper.toDto(frameRepository.save(frame));
    }

    private InventoryItemDto updateInventoryItem(InventoryItemDto inventoryItemDto) {
        final InventoryItemTypeDto inventoryItemType = new InventoryItemTypeDto();
        inventoryItemType.setTypeCode(InventoryItemType.TYPE_CODE_FRAME);
        inventoryItemDto.setInventoryItemType(inventoryItemType);
        return inventoryService.updateItem(inventoryItemDto);
    }

    @Override
    public int deduct(long id) {
        final FrameDto frame = findById(id);
        InventoryItemDto inventoryItem = frame.getInventoryItem();
        if (inventoryItem.getStock() < 1) {
            throw new GlassesShopException("not enough frames in stock");
        }
        inventoryItem.setStock(inventoryItem.getStock() - 1);
        return inventoryService.updateItem(inventoryItem).getStock();
    }

    @Override
    public FrameDto findById(final long id) {
        final EntityUser clientEntity = apiAuthenticationService.getClientEntity();
        final Frame frame = frameRepository.findById(id)
                .orElseThrow(() -> new GlassesShopException("no frame found for id " + id));
        if (!clientEntity.isAdmin() && !frame.isActive()) {
            throw new GlassesShopException("frame requested is currently incactive");
        }
        return frameMapper.toDto(frame);
    }

    @Override
    public List<FrameDto> getAvailableProducts() {
        final EntityUser clientEntity = apiAuthenticationService.getClientEntity();
        final List<Frame> frames = frameRepository.findAll();
        if (!clientEntity.isAdmin()) {
            return frames.stream().filter(Frame::isActive).map(frameMapper::toDto).collect(Collectors.toList());
        }
        return frames.stream().map(frameMapper::toDto).collect(Collectors.toList());
    }
}
