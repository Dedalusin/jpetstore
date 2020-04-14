package org.csu.jpetstore.persistence;

import org.csu.jpetstore.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {

    List<Category> getCategoryList();

    Category getCategory(String categoryId);

    void addCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(String categoryId);

}
