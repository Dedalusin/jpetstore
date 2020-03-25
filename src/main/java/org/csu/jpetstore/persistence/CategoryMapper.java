package org.csu.jpetstore.persistence;

import org.csu.jpetstore.domain.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryMapper {

    Category getCategory(String categoryId);
}
