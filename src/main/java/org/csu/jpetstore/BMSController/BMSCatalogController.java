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
    @PostMapping("/category")
    public ResultFactory addCategory(@RequestBody Category addCateForm) {
        catalogService.addCategory(addCateForm);
        return ResultFactory.successResult(null,"成功");
    }
    @PutMapping("/category")
    public ResultFactory updateCategory(@RequestBody Category addCateForm) {
        catalogService.updateCategory(addCateForm);
        return ResultFactory.successResult(null,"成功");
    }
    @DeleteMapping("/category")
    public ResultFactory deleteCategory(@RequestParam String categoryId) {
        catalogService.deleteCategory(categoryId);
        return ResultFactory.successResult(null,"成功");
    }

    @PostMapping("/product")
    public ResultFactory addProduct(@RequestBody Product addCateForm) {
        catalogService.addProduct(addCateForm);
        return ResultFactory.successResult(null,"成功");
    }
    @PutMapping("/product")
    public ResultFactory updateProduct(@RequestBody Product addCateForm) {
        catalogService.updateProduct(addCateForm);
        return ResultFactory.successResult(null,"成功");
    }
    @DeleteMapping("/product")
    public ResultFactory deleteProduct(@RequestParam String productId) {
        catalogService.deleteProduct(productId);
        return ResultFactory.successResult(null,"成功");
    }


    @PostMapping("/item")
    public ResultFactory addItem(@RequestBody Item addCateForm) {
        catalogService.addItem(addCateForm);
        return ResultFactory.successResult(null,"成功");
    }
    @PutMapping("/item")
    public ResultFactory updateItem(@RequestBody Item addCateForm) {
        catalogService.updateItem(addCateForm);
        return ResultFactory.successResult(null,"成功");
    }
    @DeleteMapping("/item")
    public ResultFactory deleteItem(@RequestParam String itemId) {
        catalogService.deleteItem(itemId);
        return ResultFactory.successResult(null,"成功");
    }

    @GetMapping("/allItem")
    public ResultFactory getAllItem(@RequestParam(value = "pagenum")int pagenum,@RequestParam(value = "pagesize")int pageSize){
        PageHelper.startPage(pagenum, pageSize);
        List<Item> itemList = catalogService.getAllItem();
        PageInfo<Item> pageInfo = new PageInfo<>(itemList);
        return ResultFactory.successResult(pageInfo,"成功");
    }
}
