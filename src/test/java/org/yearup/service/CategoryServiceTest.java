package org.yearup.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yearup.models.Category;
import org.yearup.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// methodName_StateUnderTest_ExpectedBehavior — for example byId_MissingId_ReturnsNull

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void getAllCategories_CategoriesExist_ReturnAllCategories() {
        // Arrange
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Pink Vinyl", "vinyls"));
        categories.add(new Category(2, "Blue Vinyl", "vinyl"));
        categories.add(new Category(3, "Orange Vinyl", "vinyl"));
        when(categoryRepository.findAll()).thenReturn(categories);

        // Act
        List<Category> result = categoryService.getAllCategories();

        // Assist
        assertNotNull(result);
        assertEquals(3, result.size());

        verify(categoryRepository).findAll();
    }

    @Test
    void getById_CategoriesWithIdExists_ReturnsCategory() {
        // Arrange
        Category category = new Category(1, "Pink Vinyl", "vinyls");
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));

        // Act
        Category result = categoryService.getById(1);

        // Assist
        assertNotNull(result);
        assertEquals(1, result.getCategoryId());
        assertEquals("Pink Vinyl", result.getName());
        verify(categoryRepository).findById(1);
    }

    @Test
    void getById_MissingId_ReturnsNull() {
        // Arrange
        when(categoryRepository.findById(99))
                .thenReturn(Optional.empty());

        // Act
        Category result = categoryService.getById(99);

        // Assert
        assertNull(result);

        verify(categoryRepository).findById(99);
    }

    @Test
    void create_ValidCategory_SavesCategory() {
        // Arrange
        Category category = new Category(0, "SO BE IT", "album");
        Category savedCategory = new Category(1, "Sincerely,", "album");

        when(categoryRepository.save(category)).thenReturn(savedCategory);

        // Act
        Category result = categoryService.create(category);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getCategoryId());
        assertEquals("Sincerely,", result.getName());

        verify(categoryRepository).save(category);
    }

    @Test
    void update_ExistingCategory_ReturnUpdatedCategory() {
        // Arrange
        Category existing = new Category(1, "Idea 10", "album");
        Category updated = new Category(1, "Idea 22", "single");

        when(categoryRepository.findById(1)).thenReturn(Optional.of(existing));

        when(categoryRepository.save(existing)).thenReturn(existing);

        // Act
        Category result = categoryService.update(1, updated);

        // Assert
        assertNotNull(result);
        assertEquals("Idea 22", result.getName());
        assertEquals("single", result.getDescription());

        verify(categoryRepository).findById(1);
        verify(categoryRepository).save(existing);
    }

    @Test
    void delete_ExistingId_CallsDeleteById() {
        // Act
        categoryService.delete(1);

        // Assert
        verify(categoryRepository).deleteById(1);
    }
}