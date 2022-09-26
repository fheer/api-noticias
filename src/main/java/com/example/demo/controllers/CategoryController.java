package com.example.demo.controllers;

import com.example.demo.entities.CategoryEntity;
import com.example.demo.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/getAll")
    public ResponseEntity<List<CategoryEntity>> getAllNews() {
        List<CategoryEntity> reponse = categoryService.getAllCategory();
        return new ResponseEntity<>(reponse, HttpStatus.OK);
    }

    @GetMapping(value="/getNews/{id}")
    public ResponseEntity<CategoryEntity> listById(@PathVariable("id") Integer id) {

        Optional<CategoryEntity> categoryEntity = categoryService.getCategoryById(id);
        CategoryEntity categoryEntityDtoMapper = new CategoryEntity();

        if(categoryEntity == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        // convert entity to DTO
        CategoryEntity categoryEntityDto = categoryEntityDtoMapper.mapperCategoryEntityDto(categoryEntity);

        return ResponseEntity.ok().body(categoryEntityDto);
    }

    @PostMapping(value = "/add")
    public void add(@RequestParam("category") String category, @RequestParam("description") String description){

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setCategory(category);
        categoryEntity.setDescription(description);

    }

    @RequestMapping(value = "/update")
    public void updateNews(@RequestBody CategoryEntity categoryEntity) {
        categoryService.update(categoryEntity);
    }

    /**
     * Delete method
     * @return Response Entity
     */
    @DeleteMapping(value="delete/{id}")
    public void delete(@PathVariable("id") Integer id)
    {
        categoryService.delete(id);
    }
}
