package com.letsgo.blog.service.impl;

import com.letsgo.blog.dto.CategoryDto;
import com.letsgo.blog.entity.Category;
import com.letsgo.blog.exceptions.ResourceNotFoundException;
import com.letsgo.blog.repository.CategoryRepository;
import com.letsgo.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category addedCategory = this.categoryRepository.save(category);
        CategoryDto addedCategoryDto = this.modelMapper.map(addedCategory,CategoryDto.class);
        return addedCategoryDto;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int theCategoryId) {
        Category category = this.categoryRepository.findById(theCategoryId)
                            .orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",theCategoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(int theCategoryId) {
        Category category = this.categoryRepository.findById(theCategoryId)
                            .orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",theCategoryId));
        this.categoryRepository.delete(category);

    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categoryList = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = categoryList.stream()
                .map(category-> this.modelMapper.map(category,CategoryDto.class))
                .collect(Collectors.toList());
        return categoryDtoList;
    }

    @Override
    public CategoryDto getCategoryById(int theCategoryId) {
        Category category = this.categoryRepository.findById(theCategoryId)
                            .orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",theCategoryId));
        return this.modelMapper.map(category,CategoryDto.class);
    }
}
