package org.csu.jpetstore.service;

import org.csu.jpetstore.domain.Category;
import org.csu.jpetstore.persistence.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {
    @Autowired
    private CategoryMapper categoryMapper;

    public Category getCategory(String categoryId){
        return categoryMapper.getCategory(categoryId);
    }
}
