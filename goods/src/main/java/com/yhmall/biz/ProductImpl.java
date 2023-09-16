package com.yhmall.biz;

import com.yhmall.bean.Category;
import com.yhmall.bean.Product;
import com.yhmall.dao.CategoryDao;
import com.yhmall.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(propagation = Propagation.SUPPORTS,
        isolation  = Isolation.DEFAULT,timeout = 2000,
        readOnly = true,rollbackFor = RuntimeException.class
)
public class ProductImpl implements ProductBiz {


    @Autowired
    private ProductDao productDao;
    @Autowired
    private CategoryDao categoryDao;

    @Transactional(propagation = Propagation.REQUIRED,
            isolation  = Isolation.DEFAULT,timeout = 2000,
            rollbackFor = RuntimeException.class
    )
    @Override
    public void addProduct(Product product) {
        productDao.insert(product);
    }
    @Transactional(propagation = Propagation.REQUIRED,
            isolation  = Isolation.DEFAULT,timeout = 2000,
            rollbackFor = RuntimeException.class
    )
    @Override
    public void deleteProduct(Integer productId) {
        productDao.deleteById(productId);

    }
    @Transactional(propagation = Propagation.REQUIRED,
            isolation  = Isolation.DEFAULT,timeout = 2000,
            rollbackFor = RuntimeException.class
    )
    @Override
    public void updateProduct(Product product) {
        productDao.updateById(product);
    }

    @Override
    public List<Product> findAllProductsByType(Integer parentId, Integer songId,int pageSize ,int pageNum ) {
        int start = (pageNum-1)*pageSize;
      return   productDao.findAllProductsByType(parentId,songId,start,pageSize);
    }

    @Override
    public List<Product> findAllProducts() {
        return  productDao.selectList(null);
    }

    @Override
    public Product findById(Integer id) {
        return productDao.selectById(id);
    }

    @Override
    public List<Product> findByname(String productName,int pageNum,int pageSize) {
        int start = (pageNum-1)*pageSize;
        return productDao.findProductByName(productName,start, pageSize);
    }

    @Override
    public List<Product> findProductBySpecialTop() {
        return productDao.findProductBySpecialTop();
    }

    @Override
    public List<Product> findProductBySpecial(String SpecialName,int pageNum,int pageSize) {
        int start = (pageNum-1)*pageSize;
        return productDao.findProductBySpecial(SpecialName,start,pageSize);
    }

    @Override
    public List<Product> findMostProduct() {
        return productDao.findMostProduct();
    }


    @Transactional(propagation = Propagation.REQUIRED,
            isolation  = Isolation.DEFAULT,timeout = 2000,
            rollbackFor = RuntimeException.class
    )
    @Override
    public void createCategory(Category category) {
        categoryDao.insert(category);
    }
    @Transactional(propagation = Propagation.REQUIRED,
            isolation  = Isolation.DEFAULT,timeout = 2000,
            rollbackFor = RuntimeException.class
    )
    @Override
    public void deleteCategory(Integer categoryId) {
            categoryDao.deleteById(categoryId);
    }
    @Transactional(propagation = Propagation.REQUIRED,
            isolation  = Isolation.DEFAULT,timeout = 2000,
            rollbackFor = RuntimeException.class
    )
    @Override
    public void updateCategory(Category category) {
            categoryDao.updateById(category);
    }

    @Override
    public List<Category> getCategories() {
      return   categoryDao.selectList(null);
    }
}
