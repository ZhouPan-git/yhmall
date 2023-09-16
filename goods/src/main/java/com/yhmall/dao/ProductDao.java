package com.yhmall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhmall.bean.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductDao extends BaseMapper<Product> {
   // 分页查询商品信
   @Select("SELECT \n" +
           "    p.id,\n" +
           "    p.name,\n" +
           "    p.category_id, \n" +
           "    p.description,\n" +
           "    p.price, \n" +
           "    p.stock,\n" +
           "    p.img,\n" +
           "    p.status,\n" +
           "    p.salecount,\n" +
           "    p.deliveryaddr, \n" +
           "    p.special,\n" +
           "    c.id cate_id,\n" +
           "    c.name cate_name\n" +
           "FROM product p\n" +
           "INNER JOIN category c ON p.category_id = c.id\n" +
           "WHERE c.parent_id = #{parentId}  \n" +
           "AND (\n" +
           "    #{categoryId} = 0 OR\n" +
           "    (\n" +
           "        #{categoryId} != 0 AND c.id = #{categoryId}\n" +
           "    )\n" +
           ")\n" +
           "LIMIT #{start}, #{pageSize}")
    List<Product> findAllProductsByType(@Param("parentId") Integer parentId, @Param("categoryId") Integer categoryId, @Param("start") int start, @Param("pageSize") int pageSize);

    //模糊查询商品
    @Select("SELECT * FROM product WHERE name LIKE CONCAT('%', #{name}, '%')ORDER BY CASE WHEN name = #{name} THEN 1 ELSE 2 END LIMIT #{start}, #{pageSize};\n")
    List<Product> findProductByName(String name,int start,int pageSize);
    //每种活动的前5种商品，用于显示在前端首页
    @Select("SELECT * FROM product ORDER BY special ,CAST(salecount AS SIGNED) desc LIMIT 5")
    List<Product> findProductBySpecialTop();
    //前5种销量最好的商品，用于显示在搜索框的下方
    @Select("SELECT id,name\n" +
            "FROM product\n" +
            "ORDER BY CAST(salecount AS SIGNED)  desc " +
            "LIMIT 5;")
    List<Product> findMostProduct();

    //根据活动类型分页获取商品
    @Select("SELECT * FROM product where special =#{name}  LIMIT #{start}, #{pageSize}")
    List<Product> findProductBySpecial(String name,int start,int pageSize);
}

