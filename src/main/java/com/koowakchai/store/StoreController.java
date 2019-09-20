package com.koowakchai.store;

import com.koowakchai.store.common.config.base.ResponseResult;
import com.koowakchai.store.entity.BusinessTypeEntity;
import com.koowakchai.store.entity.Product;
import com.koowakchai.store.service.BusinessTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequestMapping("/")
@RestController
public class StoreController {

    @Autowired
    private BusinessTypeService businessTypeService;

    private List<Product> productList = new ArrayList<Product>();


    @RequestMapping(method = RequestMethod.GET)
    public String showProduct(ModelMap model) {
        model.addAttribute("product", "RELX");
        return "storePage";
    }

    @ApiOperation(value = "Testing Page1")
    @RequestMapping(value = "/testPost", method = RequestMethod.POST)
    public ResponseResult testPost(@RequestParam String productName,
                               @RequestParam String make,
                               @RequestParam String description,
                               @RequestParam String price) {
        Product product = new Product();
        product.setDescription(description);
        product.setProductName(productName);
        product.setMake(make);
        product.setPrice(price);
        this.productList.add(product);
        String message="添加product成功";

        Integer result=200;
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "Testing Page2")
    @RequestMapping(value = "/testGet", method = RequestMethod.GET)
    public ResponseResult testGet() {
        String message="获取product list成功";

        Integer result=200;
        try {

            return new ResponseResult(result,message,this.productList);
        } catch (Exception e) {
            message="添加product list失败";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "Testing Page3")
    @RequestMapping(value = "/testHibernateSave", method = RequestMethod.POST)
    public ResponseResult testHibernateSave(@RequestBody BusinessTypeEntity businessTypeEntity) {
        String message="Hibernate保存BusinessType成功";

        Integer result=200;
        try {
            businessTypeService.saveOrUpdateBusinessType(businessTypeEntity);
            return new ResponseResult(result,message,null);
        } catch (Exception e) {
            message="Hibernate保存BusinessType失败";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }
}
