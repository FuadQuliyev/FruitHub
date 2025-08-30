package com.example.fruithub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private String name;
    private double price;
    private long count;
    private UUID categoryUuid;
    private UUID quantityUuid;
    private UUID currencyUuid;
    private UUID statusUuid;
}
