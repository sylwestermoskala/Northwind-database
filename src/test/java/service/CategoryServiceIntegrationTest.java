package service;

import model.Category;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryServiceIntegrationTest {

    private CategoryService categoryService;

    @Before
    public void setUp() {
        this.categoryService = new CategoryService();
    }

    @Test
    public void findsCategoryById() {
        Category category = categoryService.findById(1);
        assertThat(category.getId()).isEqualTo(1);
    }

    @Test
    public void updatesCategory() {
        //checking original name
        Category category = categoryService.findById(1);
        assertThat(category.getCategoryName()).isEqualTo("Beverages");

        //updating name
        category.setCategoryName("test_name");
        categoryService.update(category);
        category = categoryService.findById(1);
        assertThat(category.getCategoryName()).isEqualTo("test_name");

        //reseting to original
        category.setCategoryName("Beverages");
        categoryService.update(category);
    }

    @Test
    public void deletesCategoryById() {
//        //checking original exists
//        Category category = categoryService.findById(1);
//        assertThat(category).isNotNull();
//
//        //removing
//        categoryService.delete(1);
//        assertThat(categoryService.findById(1)).isNull();
    }

    @Test
    public void findsAll() {
        List<Category> categories = categoryService.findAll();
        assertThat(categories.size()).isEqualTo(8);
    }

}