package com.letsgo.blog.controller;

import com.letsgo.blog.dto.ApiResponse;
import com.letsgo.blog.dto.CategoryDto;
import com.letsgo.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //create
    @PostMapping("/create-category")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto theCategoryDto) {
        CategoryDto createdCategoryDto = this.categoryService.createCategory(theCategoryDto);
        return  new ResponseEntity<>(createdCategoryDto, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/update-category/{theCategoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto theCategoryDto,
                                                       @PathVariable int theCategoryId) {
        CategoryDto updatedCategoryDto = this.categoryService.updateCategory(theCategoryDto,theCategoryId);
        return new ResponseEntity<>(updatedCategoryDto,HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/delete-category/{theCategoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int theCategoryId) {
        this.categoryService.deleteCategory(theCategoryId);
        return new ResponseEntity<>(new ApiResponse("Category is deleted ",true),HttpStatus.OK);
    }

    //getById
    @GetMapping("/{theCategoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable int theCategoryId) {
        CategoryDto categoryDto = this.categoryService.getCategoryById(theCategoryId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }

    //getAll
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<CategoryDto> categoryDtoList = this.categoryService.getCategories();
        return new ResponseEntity<>(categoryDtoList,HttpStatus.OK);
    }
}
