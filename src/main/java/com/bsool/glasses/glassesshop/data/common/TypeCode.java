package com.bsool.glasses.glassesshop.data.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class TypeCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type_code", nullable = false, unique = true)
    private int typeCode;

    @Column(name = "type_name", nullable = false)
    private String typeName;


    public TypeCode(final int typeCode) {
        this.typeCode = typeCode;
    }
}