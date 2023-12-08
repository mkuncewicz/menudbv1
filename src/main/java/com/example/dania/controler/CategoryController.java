package com.example.dania.controler;

import com.example.dania.dto.CategoryDto;
import com.example.dania.entity.Category;
import com.example.dania.mapper.CategoryMapper;
import com.example.dania.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/category")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryMapper.mapToListDto(categoryService.getAllCategories()));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long categoryId){
        Category category = categoryService.getCategory(categoryId);
        if (category != null){
            return ResponseEntity.ok(categoryMapper.mapToDto(category));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/image/{categoryId}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long categoryId){
        Category category = categoryService.getCategory(categoryId);
        byte[] imageData = category.getImage();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){

        return ResponseEntity.ok(categoryMapper.mapToDto(
                categoryService.createCategory(
                        categoryMapper.mapToEntity(categoryDto)
                )));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDto categoryDto){
        Category saveCategory = categoryService.updateCategory(categoryId,categoryMapper.mapToEntity(categoryDto));
        if (saveCategory != null){
            return ResponseEntity.ok(categoryMapper.mapToDto(saveCategory));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId){
        boolean check = categoryService.deleteCategory(categoryId);

        if (check){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
