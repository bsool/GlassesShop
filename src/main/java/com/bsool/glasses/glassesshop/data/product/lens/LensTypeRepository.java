package com.bsool.glasses.glassesshop.data.product.lens;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LensTypeRepository extends JpaRepository<LensType, Long> {
    Optional<LensType> findByTypeCode(int type_code);
}