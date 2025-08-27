package com.example.fruithub.controller;


import com.example.fruithub.dto.CategoryDto;
import com.example.fruithub.model.Category;
import com.example.fruithub.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getCategory(){
        List<Category> categories = categoryService.getAllCategory();
        if (categories == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/add")
    public void addCategory(@RequestBody CategoryDto category){
        categoryService.addCategory(category);
    }

    @PutMapping("/update/{uuid}")
    public void updateCategory(@PathVariable UUID uuid, @RequestBody CategoryDto category){
        categoryService.updateCategory(uuid, category);
    }

    @DeleteMapping("/delete/{uuid}")
    public void deleteCategory(@PathVariable UUID uuid){
        categoryService.deleteCategory(uuid);
    }

}
