package com.example.th3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.th3.entity.Category;
import com.example.th3.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(categoryDetails.getName());
            category.setDesc(categoryDetails.getDesc());
            return categoryRepository.save(category);
        }
        throw new RuntimeException("Category not found with id: " + id);
    }

    public void deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Category not found with id: " + id);
        }
    }

    public List<Category> searchByName(String name) {
        return categoryRepository.findByNameContainingIgnoreCase(name);
    }
}


