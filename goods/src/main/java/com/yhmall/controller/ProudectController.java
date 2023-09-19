package com.yhmall.controller;

import com.yhmall.bean.Category;
import com.yhmall.bean.Product;
import com.yhmall.biz.ProductBiz;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("product")
@Slf4j
public class ProudectController {
    @Autowired
    private ProductBiz productBiz;

    /**
     * 通过商品id查询商品的具体详细
     * @param productid
     * @return
     */
    @GetMapping("findProductById/{productid}")
    public Map<String, Object> findProductById(@PathVariable Integer productid){
        Map<String, Object> map = new HashMap<String, Object>();
        Product product = null;
        try {
        product =  productBiz.findById(productid);
        }catch(Exception e){
            e.printStackTrace();
            map.put("code", 0);
            map.put("msg",e.getCause());
        }
        map.put("code",1);
        map.put("data",product);
        return map;
    }

    /**
     * 查询所有的种类，包括一级种类和二级种类
     * @return
     */
    @GetMapping("getCategories")
    public Map<String, Object> getCategories() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Category> categorys = null;

        try {
            categorys = productBiz.getCategories();
            List<Category> result = new ArrayList<>();

            for (int i = 0; i < categorys.size(); i++) {
                if (categorys.get(i).getParentid() == 0) {
                    List<Category> sonncategorys = new ArrayList<>(); // 创建新的子类别列表
                    for (int j = 0; j < categorys.size(); j++) {
                        if (categorys.get(i).getId() == categorys.get(j).getParentid()) {
                            sonncategorys.add(categorys.get(j));
                        }
                    }
                    categorys.get(i).setSonCategorys(sonncategorys);
                    result.add(categorys.get(i));
                }
            }
            map.put("code", 1);
            map.put("data", result);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 0);
            map.put("msg", e.getCause());
        }

        return map;
    }

    /**
     * 通过二级类别分页 排序 查找 商品
     * @param parentId
     * @param songId
     * @param pageSize
     * @param pageNum
     *
     * @return
     */
    @PostMapping("findAllProductsByType")
    public Map<String, Object> findAllProductsByType(@RequestParam("parentId")Integer parentId, @RequestParam("songId") Integer songId,
                                                     @RequestParam("pageSize")  int pageSize ,@RequestParam("pageNum")int pageNum){
        Map<String, Object> map = new HashMap<String, Object>();
       List<Product>  products = null;
        try {
            products =  productBiz.findAllProductsByType(parentId,songId,pageSize,pageNum);
        }catch(Exception e){
            e.printStackTrace();
            map.put("code", 0);
            map.put("msg",e.getCause());
        }
        map.put("code",1);
        map.put("data",products);
        return map;
    }

    @PostMapping("findAllProducts")
    public Map<String, Object> findAllProducts(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Product>  products = null;
        try {
            products =  productBiz.findAllProducts();
        }catch(Exception e){
            e.printStackTrace();
            map.put("code", 0);
            map.put("msg",e.getCause());
        }
        map.put("code",1);
        map.put("data",products);
        return map;
    }

    @PostMapping("findByname")
    public Map<String, Object> findByname(@RequestParam("productName")String productName,@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Product>  products = null;
        try {
            products =  productBiz.findByname(productName, pageNum, pageSize);
        }catch(Exception e){
            e.printStackTrace();
            map.put("code", 0);
            map.put("msg",e.getCause());
        }
        map.put("code",1);
        map.put("data",products);
        return map;
    }
    @PostMapping("findProductBySpecial")
    public Map<String, Object> findProductBySpecial(@RequestParam("specialName")String specialName, @RequestParam("pageSize")  int pageSize ,
                                                    @RequestParam("pageNum")int pageNum){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Product>  products = null;
        try {
            products =  productBiz.findProductBySpecial(specialName,pageSize,pageNum);
        }catch(Exception e){
            e.printStackTrace();
            map.put("code", 0);
            map.put("msg",e.getCause());
        }
        map.put("code",1);
        map.put("data",products);
        return map;
    }


@PostMapping("findProductBySpecialTop")
public Map<String, Object> findProductBySpecialTop(@Param(value = "num") int num) {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Product> products = null;
    Map<String, List<Product>> result = new HashMap<String, List<Product>>();

    try {
        products = productBiz.findProductBySpecialTop(num);

        for (Product product : products) {
            String special = product.getSpecial();
            if (!result.containsKey(special)) {
                result.put(special, new ArrayList<Product>());
            }
            result.get(special).add(product);
        }

    } catch (Exception e) {
        e.printStackTrace();
        map.put("code", 0);
        map.put("msg", e.getCause());
    }
    map.put("code", 1);
    map.put("data", result);
    return map;
}



    @PostMapping("findMostProduct")
    public Map<String, Object> findMostProduct(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Product>  products = null;
        try {
            products =  productBiz.findMostProduct();
        }catch(Exception e){
            e.printStackTrace();
            map.put("code", 0);
            map.put("msg",e.getCause());
        }
        map.put("code",1);
        map.put("data",products);
        return map;
    }
}

