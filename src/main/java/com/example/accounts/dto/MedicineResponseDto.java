package com.example.accounts.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MedicineResponseDto {
    private Long medicineId;
    private String medicineName;
    private Long categoryId;
    private String manufacturer;
    private String formula;
    private Date mfdDate;
    private Date expiryDate;
    private String dosage;
    private Long quantity;
    private String unit;
    private Long totalQuantity;
    private Long costPerUnit;
    private Boolean isInStock;
    private String categoryName;
}