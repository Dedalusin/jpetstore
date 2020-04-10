package org.csu.jpetstore.BMSController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.csu.jpetstore.domain.Category;
import org.csu.jpetstore.domain.Item;
import org.csu.jpetstore.domain.Product;
import org.csu.jpetstore.service.CatalogService;
import org.csu.jpetstore.utils.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/catalog")
public class BMSCatalogController {
    @Autowired
    private CatalogService catalogService;

    @GetMapping("/category")
    public ResultFactory viewCategory(@RequestParam(value = "pagenum")int pagenum,@RequestParam(value = "pagesize")int pageSize) {
        JSONObject data = new JSONObject();
//        System.out.println(params.toString());
//        int pageNum = (int)params.get("pagenum");
//        int pageSize = (int)params.get("pagesize");
        PageHelper.startPage(pagenum, pageSize);
        List<Category> categoryList= catalogService.getCategoryList();
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
//        JSONObject jsonCategory= (JSONObject)pageInfo.toString();
        data.put("children",pageInfo);
        return ResultFactory.successResult(pageInfo,"查询成功");
    }
    @GetMapping("/product")
    public ResultFactory viewPoroduct(@RequestParam(value = "pagenum")int pagenum,@RequestParam(value = "pagesize")int pageSize,@RequestParam(value = "categoryId")String categoryId) {
        PageHelper.startPage(pagenum, pageSize);
        List<Product> productsList= catalogService.getProductListByCategory(categoryId);
        PageInfo<Product> pageInfo = new PageInfo<>(productsList);
        return ResultFactory.successResult(pageInfo,"查询成功");
    }
    @GetMapping("/item")
    public ResultFactory viewItem(@RequestParam(value = "pagenum")int pagenum,@RequestParam(value = "pagesize")int pageSize,@RequestParam(value = "productId")String productId) {
        PageHelper.startPage(pagenum, pageSize);
        List<Item> itemList= catalogService.getItemListByProduct(productId);
        PageInfo<Item> pageInfo = new PageInfo<>(itemList);
        return ResultFactory.successResult(pageInfo,"查询成功");
    }

}
