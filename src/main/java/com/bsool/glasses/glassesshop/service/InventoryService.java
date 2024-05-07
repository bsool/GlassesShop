package com.bsool.glasses.glassesshop.service;

import com.bsool.glasses.glassesshop.data.inventory.InventoryItem;
import com.bsool.glasses.glassesshop.data.inventory.InventoryItemDto;
import com.bsool.glasses.glassesshop.data.inventory.InventoryItemMapper;
import com.bsool.glasses.glassesshop.data.inventory.InventoryItemRepository;
import com.bsool.glasses.glassesshop.data.inventory.InventoryItemType;
import com.bsool.glasses.glassesshop.data.inventory.InventoryItemTypeRepository;
import com.bsool.glasses.glassesshop.exception.GlassesShopException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemTypeRepository inventoryItemTypeRepository;
    private final InventoryItemMapper inventoryItemMapper;


    public List<InventoryItemDto> getInventory(final Long id) {
        if (id != null) {
            return Collections.singletonList(getInventoryItemById(id));
        }
        return getInventoryList();
    }

    public List<InventoryItemDto> getInventoryList() {
        return inventoryItemRepository.findAll().stream().map(inventoryItemMapper::toDto).collect(Collectors.toList());
    }

    public InventoryItemDto getInventoryItemById(long id) {
        return inventoryItemMapper.toDto(inventoryItemRepository.findById(id).orElseThrow(() -> new GlassesShopException("type code not found")));
    }

    public InventoryItemDto updateItem(final InventoryItemDto itemDto) {
        final InventoryItem inventoryItem = inventoryItemMapper.toEntity(itemDto);
        final int typeCode = itemDto.getInventoryItemType().getTypeCode();
        final InventoryItemType inventoryItemType = inventoryItemTypeRepository.findByTypeCode(typeCode).orElseThrow(() -> new GlassesShopException("type code not found"));
        inventoryItem.setInventoryItemType(inventoryItemType);
        return inventoryItemMapper.toDto(inventoryItemRepository.save(inventoryItem));
    }
}
