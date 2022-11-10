package com.letsgo.blog.service;

import com.letsgo.blog.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    //in interface all the methods are public by default

    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto, int theCategoryId);

    //delete
    void deleteCategory(int theCategoryId);

    //getAll
    List<CategoryDto> getCategories();

    //getById
    CategoryDto getCategoryById(int theCategoryId);
}
