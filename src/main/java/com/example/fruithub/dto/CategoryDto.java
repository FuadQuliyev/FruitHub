package com.example.fruithub.dto;

import lombok.*;

import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private String name;
    private String description;
    private UUID categoryUuid;
    private UUID statusUuid;
}
