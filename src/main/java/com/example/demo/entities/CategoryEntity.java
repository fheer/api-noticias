package com.example.demo.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Optional;

@Data
@Entity
@Table(name="category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategory")
    private Integer idcategory;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    public CategoryEntity mapperCategoryEntityDto(Optional<CategoryEntity> categoryEntity) {

        CategoryEntity categoryDto = new CategoryEntity();

        categoryDto.setCategory(categoryEntity.get().getCategory());
        categoryDto.setDescription(categoryEntity.get().getDescription());

        return categoryDto;
    }
}
