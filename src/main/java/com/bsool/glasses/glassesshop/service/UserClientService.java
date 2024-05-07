package com.bsool.glasses.glassesshop.service;

import com.bsool.glasses.glassesshop.data.common.ProductDto;

import java.util.List;

public interface UserClientService<k extends ProductDto> {

    int deduct(final long id);

    k findById(final long id);

    List<k> getAvailableProducts();
}
