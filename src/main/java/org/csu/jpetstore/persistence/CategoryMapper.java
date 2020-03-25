package org.csu.jpetstore.persistence;

import org.csu.jpetstore.domain.Category;
import org.springframework.stereotype.Repository;

@Repository //让它被当成bean扫描出来
public interface CategoryMapper {

    Category getCategory(String categoryId);
}
