package ru.gb.springdatajpa.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;

    @NotNull
    @NotEmpty(message = "{validation.NotEmpty.message}")
    private String title;

    @Min(value = 0, message = "{validation.Min.message}")
    private Float price;

}