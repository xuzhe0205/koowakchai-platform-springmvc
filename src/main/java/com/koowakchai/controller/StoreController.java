package com.koowakchai.controller;

import com.koowakchai.common.base.ResponseResult;
import com.koowakchai.hibernate.entity.*;
import com.koowakchai.store.service.TBusinessService;
import com.koowakchai.store.service.TECigaretteService;
import com.koowakchai.store.service.TLiquorService;
import com.koowakchai.store.service.TShoppingCartService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/store")
@RestController
public class StoreController {

    @Autowired
    private TBusinessService tBusinessService;

    @Autowired
    private TShoppingCartService tShoppingCartService;

    @Autowired
    private TECigaretteService teCigaretteService;

    @Autowired
    private TLiquorService tLiquorService;


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
    public ResponseResult addToCart(@ApiParam(required = true,name = "userId",value="user id") @RequestParam("userId") long userId,
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

    @ApiOperation(value = "Get item from cart")
    @RequestMapping(value = "/getCartItem", method = RequestMethod.GET)
    public ResponseResult addToCart(@ApiParam(required = true,name = "userId",value="user id") @RequestParam("userId") long userId) {
        String message="提取该用户购物车商品成功";

        Integer result=200;
        try {
            List<ShoppingCartItemEntity> tShoppingCartEntityList = tShoppingCartService.getCartItem(userId);

            return new ResponseResult(result,message,tShoppingCartEntityList);
        } catch (Exception e) {
            message="提取该用户购物车商品失败";
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

    @ApiOperation(value = "getSortedProductsByType")
    @RequestMapping(value = "/getSortedProductsByType", method = RequestMethod.GET)
    public ResponseResult getSortedProductsByType(@ApiParam(required = true,name = "productType",value="Product Type") @RequestParam("productType") String productType,
                                                  @ApiParam(required = false,name = "sortedKey",value="Key to sort") @RequestParam("sortedKey") String sortedKey) {
        String message=" 获取所有Product 并 sort 成功";

        Integer result=200;

        if (sortedKey.equals("") || sortedKey == null){
            sortedKey = "salesVol";
        }

        try {
            if (productType.equals("ecigarette")){
                List<TECigaretteEntity> teCigaretteEntityList = teCigaretteService.getTEcigaEntitySorted(sortedKey);
                return new ResponseResult(result,message, teCigaretteEntityList);
            }
            else if (productType.equals("liquor")){
                List<TLiquorEntity> tLiquorEntityList = tLiquorService.getTLiquorEntitySorted(sortedKey);
                return new ResponseResult(result,message, tLiquorEntityList);
            }
        } catch (Exception e) {
            message="获取所有Product 并 sort 失败";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }




}
