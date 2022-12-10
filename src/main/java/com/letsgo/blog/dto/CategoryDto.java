package com.letsgo.blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private int categoryId;
    @NotBlank
    @Size(min = 4, max = 10, message = "minimum 4 chars and maximum 10 chars are allowed")
    private String categoryTitle;

    @NotBlank
    @Size(min = 10, max = 30, message = "minimum 10 chars and maximum 30 chars are allowed")
    private String categoryDescription;

}
