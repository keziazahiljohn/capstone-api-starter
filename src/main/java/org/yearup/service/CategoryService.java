package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.Category;
import org.yearup.models.Product;
import org.yearup.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService
{
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories()
    {
        return categoryRepository.findAll();
    }

    public Category getById(int categoryId)
    {
        return categoryRepository.findById(categoryId).orElse(null);
    }
// dont need (accidentally created by copying sneaker drops XD)
//    public List<Category> search(String name, String description){
//        return categoryRepository.findAll().stream()
//                .filter(category -> name == null || category.getName().toLowerCase().contains(name.toLowerCase()))
//                .filter(category -> description == null || category.getDescription().toLowerCase().contains(description.toLowerCase()))
//                .toList();
//    }

    // CRUD

    public Category create(Category category)
    {
        return categoryRepository.save(category);
    }

    public Category update(int categoryId, Category category)
    {
        Category existing = getById(categoryId);

        if (existing == null) return null;

        existing.setName(category.getName());
        existing.setDescription(category.getDescription());

        return categoryRepository.save(existing);
    }

    public void delete(int categoryId)
    {
        categoryRepository.deleteById(categoryId);
    }
}
