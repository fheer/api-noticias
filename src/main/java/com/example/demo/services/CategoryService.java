package com.example.demo.services;

import com.example.demo.entities.CategoryEntity;

import com.example.demo.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryEntity> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Optional<CategoryEntity> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }

    public CategoryEntity register(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    public CategoryEntity update(@RequestBody CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
}
