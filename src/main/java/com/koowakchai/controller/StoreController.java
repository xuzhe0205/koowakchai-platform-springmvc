package com.koowakchai.controller;

import com.koowakchai.store.common.config.base.ResponseResult;
import com.koowakchai.store.entity.TBusinessEntity;
import com.koowakchai.store.service.TBusinessService;
import com.koowakchai.store.service.TShoppingCartService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/")
@RestController
public class StoreController {

    @Autowired
    private TBusinessService tBusinessService;

    @Autowired
    private TShoppingCartService tShoppingCartService;


    @RequestMapping(method = RequestMethod.GET)
    public String showProduct(ModelMap model) {
        model.addAttribute("product", "RELX");
        return "storePage";
    }

    @ApiOperation(value = "Testing Page3")
    @RequestMapping(value = "/testHibernateSave", method = RequestMethod.POST)
    public ResponseResult testHibernateSave(@RequestBody TBusinessEntity businessEntity) {
        String message="Hibernate保存BusinessType成功";

        Integer result=200;
        try {
            tBusinessService.saveOrUpdateTBusinessType(businessEntity);
            return new ResponseResult(result,message,null);
        } catch (Exception e) {
            message="Hibernate保存BusinessType失败";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "Add item to cart")
    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public ResponseResult addToCart(@ApiParam(required = true,name = "userId",value="user id") @RequestParam("userId") int userId,
                                    @ApiParam(required = true,name = "subtypeId",value="product subtype id") @RequestParam("subtypeId") int subtypeId,
                                    @ApiParam(required = true,name = "productId",value="product id") @RequestParam("productId") int productId,
                                    @ApiParam(required = true,name = "quantity",value="quantity of product to be added") @RequestParam("quantity") int quantity) {
        String message="添加购物车成功";

        Integer result=200;
        try {
            tShoppingCartService.saveOrUpdateCart(userId, subtypeId, productId, quantity);

            return new ResponseResult(result,message,null);
        } catch (Exception e) {
            message="添加购物车失败";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "getProductTypes")
    @RequestMapping(value = "/getProductTypes", method = RequestMethod.GET)
    public ResponseResult getProductTypes(@ApiParam(required = true,name = "subtypeId",value="subtype id") @RequestParam("subtypeId") int subtypeId) {
        String message=" 获取product type成功";

        Integer result=200;
        try {
            List<TBusinessEntity> tBusinessEntityList = tBusinessService.getProductTypes(subtypeId);
            return new ResponseResult(result,message,tBusinessEntityList);
        } catch (Exception e) {
            message="获取product type失败";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "getAllBusinessTypes")
    @RequestMapping(value = "/getAllBusinessTypes", method = RequestMethod.GET)
    public ResponseResult getAllBusinessTypes() {
        String message=" 获取所有 business type成功";

        Integer result=200;
        try {
            List<String> tBusinessEntityList = tBusinessService.getAllBusinessTypes();
            return new ResponseResult(result,message,tBusinessEntityList);
        } catch (Exception e) {
            message="获取所有 business type失败";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }


}
