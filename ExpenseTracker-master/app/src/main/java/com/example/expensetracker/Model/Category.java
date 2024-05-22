package com.example.expensetracker.Model;

/**
 * Represents a category with a name, image, and color.
 */
public class Category {

    private String categoryName;
    private int categoryImage;
    private int categoryColor;

    /**
     * Default constructor for Category.
     */
    public Category() {
    }

    /**
     * Constructs a new Category with the specified name, image, and color.
     *
     * @param categoryName The name of the category.
     * @param categoryImage The image associated with the category.
     * @param categoryColor The color associated with the category.
     */
    public Category(String categoryName, int categoryImage, int categoryColor) {
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        this.categoryColor = categoryColor;
    }

    /**
     * Returns the name of the category.
     *
     * @return The category name.
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the name of the category.
     *
     * @param categoryName The new category name.
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Returns the image associated with the category.
     *
     * @return The category image.
     */
    public int getCategoryImage() {
        return categoryImage;
    }

    /**
     * Sets the image associated with the category.
     *
     * @param categoryImage The new category image.
     */
    public void setCategoryImage(int categoryImage) {
        this.categoryImage = categoryImage;
    }

    /**
     * Returns the color associated with the category.
     *
     * @return The category color.
     */
    public int getCategoryColor() {
        return categoryColor;
    }

    /**
     * Sets the color associated with the category.
     *
     * @param categoryColor The new category color.
     */
    public void setCategoryColor(int categoryColor) {
        this.categoryColor = categoryColor;
    }
}
