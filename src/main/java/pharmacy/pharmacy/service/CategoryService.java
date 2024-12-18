package pharmacy.pharmacy.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pharmacy.pharmacy.dao.CategoryRepository;
import pharmacy.pharmacy.entity.Category;
import pharmacy.pharmacy.exception.ProductSaveException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // Constructor injection
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Fetch all Categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Fetch a Category by ID
    public Optional<Category> getCategoryById(UUID id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> getCategoryBySlug(String slug) {
        return categoryRepository.findBySlug(slug);
    }

    // Save or update a Category
    public Category saveCategory(Category category) {
        try {
            // Generate the slug
            String slug = category.getName().toLowerCase().replaceAll("[^a-z0-9]+", "-")
                    .replaceAll("-$", "");

            // Set the slug on the product entity
            category.setSlug(slug);

            return categoryRepository.save(category);

        } catch (DataIntegrityViolationException ex) {
            // Handle database constraint violations
            throw new RuntimeException("Database error while saving product: " + ex.getMessage(), ex);
        } catch (NullPointerException ex) {
            // Handle specific null pointer exception
            throw new RuntimeException("Category or its name cannot be null", ex);
        } catch (Exception ex) {
            // Catch other unexpected exceptions
            throw new RuntimeException("An unexpected error occurred while saving the product", ex);
        }
    }


    // Delete a Category by ID
    public void deleteCategoryById(UUID id) {
        categoryRepository.deleteById(id);
    }
}
