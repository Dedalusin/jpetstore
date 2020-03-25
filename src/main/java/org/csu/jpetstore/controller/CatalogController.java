package org.csu.jpetstore.controller;


import org.csu.jpetstore.domain.Category;
import org.csu.jpetstore.domain.Product;
import org.csu.jpetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/view")
    public String view(){
        return "catalog/main";
    }

    @GetMapping("/viewCategory")
    public String viewCategory(String categoryId, Model model){
        if (categoryId!=null){
            Category category = catalogService.getCategory(categoryId);
            List<Product> productList = catalogService.getProductListByCategory(categoryId);
            model.addAttribute("category",category);
            model.addAttribute("productList",productList);
            return "catalog/category";
        }else {
            return "catalog/main";
        }
    }

}
