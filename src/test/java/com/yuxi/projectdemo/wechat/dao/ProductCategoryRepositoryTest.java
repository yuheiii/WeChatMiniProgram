package com.yuxi.projectdemo.wechat.dao;

import com.yuxi.projectdemo.wechat.dataObject.ProductCategory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public ProductCategory findOneTest() {
        Optional<ProductCategory> productCategory = repository.findById(1);
        System.out.println(productCategory.toString());
        return productCategory.orElse(null);
    }

    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        //productId is auto-increment
        productCategory.setCategoryName("category1");
        productCategory.setCategoryType(3);
        repository.save(productCategory);
    }

    @Test
    public void updateTest() {
        //should find out if this productCategory is valid, then do the update
        ProductCategory productCategory = repository.findById(3).orElse(null);
        productCategory.setCategoryType(3);
        repository.save(productCategory);

        //the use of @DynamicUpdate: if you never update a row(the updated value is the same), then the time
        //will not be dynamically updated
    }

    @Test
    @Transactional
    public void saveTest2() {
        ProductCategory productCategory = new ProductCategory("category2", 4);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(2, 3, 4);

        List<ProductCategory> result = repository.findByCategoryTypeIn(list);

        Assert.assertNotEquals(0, result.size());
    }

}