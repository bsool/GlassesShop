package com.bsool.glasses.glassesshop.data.product.lens;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrescriptionTypeRepository extends JpaRepository<PrescriptionType, Long> {
    Optional<PrescriptionType> findByTypeCode(int type_code);


}