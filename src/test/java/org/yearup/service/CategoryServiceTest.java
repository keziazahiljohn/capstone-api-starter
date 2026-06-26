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
    void getAllCategories_CategoriesExist_ReturnAllCategories(){
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
}