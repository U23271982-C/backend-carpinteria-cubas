package com.content.sale_service.common;

import lombok.Getter;

import java.util.List;

@Getter
public enum TypesTax {
    IGV(0.18);

    private double numTax;

    TypesTax(double numTax) {
    }

    public static double calulateTax(double amount, List<TypesTax> taxes){
        double total_taxes = taxes.stream().mapToDouble(TypesTax::getNumTax).sum();

        return amount * total_taxes;
    }
}
