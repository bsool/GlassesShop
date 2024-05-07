package com.bsool.glasses.glassesshop.data.product.lens;

import com.bsool.glasses.glassesshop.data.common.TypeCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "lens_type")
public class LensType extends TypeCode {
}