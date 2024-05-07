package com.bsool.glasses.glassesshop.data.product.purchase;

import com.bsool.glasses.glassesshop.data.common.TypeCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name = "currency_type")
public class CurrencyType extends TypeCode {
}
