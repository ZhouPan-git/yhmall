package com.yhmall.biz;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhmall.bean.Category;
import com.yhmall.bean.Product;

import java.util.List;
import java.util.Locale;

public interface Products {

    //商品的增删改查操作


    /**
     * 商品的添加操作
     * @param product
     */
    public void addProduct(Product product);

    /**
     * 商品的删除操作
     * @param productId
     */
    public void deleteProduct(Integer productId);


    /**
     * 商品的更新操作
     * @param product
     */
    public void updateProduct(Product product);


    /**
     * 通过两级分类查询商品操作
     * @param parentId
     * @param songId
     * @param pageSize
     * @param pageNum
     * @param sort
     * @param sortby
     * @return
     */
    public List<Product> findAllProductsByType(Integer parentId, Integer songId,int pageSize ,int pageNum ,int sort,String sortby);


    /**
     * 查询所有的商品操作
     * @return
     */
    public List<Product> findAllProducts();
   //

    /**
     * 通过固定的查询商品
     * @param id
     * @return
     */
    public Product findById(Integer id);
    //

    /**
     * 通过商品名查询商品操作(模糊查询)
     * @param productName
     * @return
     */
    public List<Product> findByname(String productName);

    /**
     * 查询不同活动商品的前5个
     * @return
     */
    public List<Product> findProductBySpecialTop();

    /**
     * 分页查询具体活动的商品
     * @param SpecialName
     * @param start
     * @param pageSize
     * @param sort
     * @param sortby
     * @return
     */
    public List<Product> findProductBySpecial(String SpecialName,int start,int pageSize,int sort,String sortby);

    /**
     * 查询最多的5个商品的id 和 名字
     * @return
     */
     public List<Product> findMostProduct();

    //商品种类的操作



    /**
     * 创建新一级种类
     * @param category
     */
    public void createCategory(Category category);

    /**
     * 删除种类
     * @param categoryId
     */
    public void deleteCategory(Integer categoryId);

    /**
     * 更新种类
     * @param category
     */
    public void updateCategory(Category category);

    /**
     * 获取所有种类
     * @return
     */
    public List<Category> getCategories();



}
