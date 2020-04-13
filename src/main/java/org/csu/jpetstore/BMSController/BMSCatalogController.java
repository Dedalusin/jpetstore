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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
// TODO 改成RESTFUL的api格式
@RestController
@RequestMapping("/catalog")
public class BMSCatalogController {
    @Autowired
    private CatalogService catalogService;

    @GetMapping("/categories")
    public ResultFactory viewCategory(@RequestParam(value = "pagenum")int pagenum,@RequestParam(value = "pagesize")int pageSize) {
//        JSONObject data = new JSONObject();
//        System.out.println(params.toString());
//        int pageNum = (int)params.get("pagenum");
//        int pageSize = (int)params.get("pagesize");
        PageHelper.startPage(pagenum, pageSize);
        List<Category> categoryList= catalogService.getCategoryList();
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
//        JSONObject jsonCategory= (JSONObject)pageInfo.toString();
//        data.put("children",pageInfo);
        return ResultFactory.successResult(pageInfo,"查询成功");
    }
    @GetMapping("/products")
    public ResultFactory viewProduct(@RequestParam(value = "pagenum")int pagenum,@RequestParam(value = "pagesize")int pageSize,@RequestParam(value = "categoryId")String categoryId) {
        PageHelper.startPage(pagenum, pageSize);
        List<Product> productsList= catalogService.getProductListByCategory(categoryId);
        PageInfo<Product> pageInfo = new PageInfo<>(productsList);
        return ResultFactory.successResult(pageInfo,"查询成功");
    }
    @GetMapping("/items")
    public ResultFactory viewItem(@RequestParam(value = "pagenum")int pagenum,@RequestParam(value = "pagesize")int pageSize,@RequestParam(value = "productId")String productId) {
        PageHelper.startPage(pagenum, pageSize);
        List<Item> itemList= catalogService.getItemListByProduct(productId);
        PageInfo<Item> pageInfo = new PageInfo<>(itemList);
        return ResultFactory.successResult(pageInfo,"查询成功");
    }
    @PostMapping("/categories")
    public ResultFactory addCategory(@RequestBody Category addCateForm) {
        catalogService.addCategory(addCateForm);
        return ResultFactory.successResult(null,"成功");
    }
    @PutMapping("/categories")
    public ResultFactory updateCategory(@RequestBody Category addCateForm) {
        catalogService.updateCategory(addCateForm);
        return ResultFactory.successResult(null,"成功");
    }
    @DeleteMapping("/categories")
    public ResultFactory deleteCategory(@RequestParam String categoryId) {
        catalogService.deleteCategory(categoryId);
        return ResultFactory.successResult(null,"成功");
    }

    @PostMapping("/products")
    public ResultFactory addProduct(@RequestBody Product addCateForm) {
        catalogService.addProduct(addCateForm);
        return ResultFactory.successResult(null,"成功");
    }
    @PutMapping("/products")
    public ResultFactory updateProduct(@RequestBody Product addCateForm) {
        catalogService.updateProduct(addCateForm);
        return ResultFactory.successResult(null,"成功");
    }
    @DeleteMapping("/products")
    public ResultFactory deleteProduct(@RequestParam String productId) {
        catalogService.deleteProduct(productId);
        return ResultFactory.successResult(null,"成功");
    }


    @PostMapping("/items")
    public ResultFactory addItem(@RequestBody Item addCateForm) {
        catalogService.addItem(addCateForm);
        return ResultFactory.successResult(null,"成功");
    }
    @PutMapping("/items")
    public ResultFactory updateItem(@RequestBody Item addCateForm) {
        catalogService.updateItem(addCateForm);
        return ResultFactory.successResult(null,"成功");
    }
    @DeleteMapping("/items")
    public ResultFactory deleteItem(@RequestParam String itemId) {
        catalogService.deleteItem(itemId);
        return ResultFactory.successResult(null,"成功");
    }

    @GetMapping("/allItems")
    public ResultFactory getAllItem(@RequestParam(value = "pagenum")int pagenum,@RequestParam(value = "pagesize")int pageSize){
        PageHelper.startPage(pagenum, pageSize);
        List<Item> itemList = catalogService.getAllItem();
        PageInfo<Item> pageInfo = new PageInfo<>(itemList);
        return ResultFactory.successResult(pageInfo,"成功");
    }

    // 已在前端实现
//    @GetMapping("/Items/{keywords}")
//    public ResultFactory searchItem(@RequestParam(value = "pagenum")int pagenum,@RequestParam(value = "pagesize")int pageSize,@PathVariable String keywords){
//        PageHelper.startPage(pagenum, pageSize);
//        List<Item> itemList = catalogService.searchItemList(keywords);
//        PageInfo<Item> pageInfo = new PageInfo<>(itemList);
//        return ResultFactory.successResult(pageInfo,"成功");
//    }
}
