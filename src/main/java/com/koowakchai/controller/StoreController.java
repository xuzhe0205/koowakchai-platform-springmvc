package com.koowakchai.controller;

import com.auth0.jwt.interfaces.Claim;
import com.koowakchai.common.base.ResponseResult;
import com.koowakchai.common.util.JWTUtils;
import com.koowakchai.hibernate.entity.*;
import com.koowakchai.store.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/store")
@RestController
@EnableAsync
public class StoreController {

    @Autowired
    private TBusinessService tBusinessService;

    @Autowired
    private TShoppingCartService tShoppingCartService;

    @Autowired
    private TECigaretteService teCigaretteService;

    @Autowired
    private TLiquorService tLiquorService;

    @Autowired
    private TTotalOrderService tTotalOrderService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private TLogisticsOrderService tLogisticsOrderService;

    private JWTUtils jwtUtils;

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
    public ResponseResult addToCart(@ApiParam(required = true,name = "authorization",value="authorization") @RequestParam("authorization") String authorization,
                                    @ApiParam(required = true,name = "subtypeId",value="product subtype id") @RequestParam("subtypeId") int subtypeId,
                                    @ApiParam(required = true,name = "productId",value="product id") @RequestParam("productId") int productId,
                                    @ApiParam(required = true,name = "quantity",value="quantity of product to be added") @RequestParam("quantity") int quantity) {
        String message="添加购物车成功";

        Integer result=200;
        try {
            Map<String, Claim> claims = jwtUtils.verifyToken(authorization);
            long userId = Long.parseLong(claims.get("userId").asString());
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
    public ResponseResult getCartItem(@ApiParam(required = true,name = "authorization",value="authorization") @RequestParam("authorization") String authorization) {
        String message="提取该用户购物车商品成功";

        Integer result=200;
        try {
            Map<String, Claim> claims = jwtUtils.verifyToken(authorization);
            long userId = Long.parseLong(claims.get("userId").asString());
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


    @ApiOperation(value = "getSortedProductsByType")
    @RequestMapping(value = "/getSortedProductsByType", method = RequestMethod.GET)
    public ResponseResult getSortedProductsByType(@ApiParam(required = true,name = "productType",value="Product Type") @RequestParam("productType") String productType,
                                                  @ApiParam(required = false,name = "sortedKey",value="Key to sort") @RequestHeader(value = "sortedKey",required = false,defaultValue = "") String sortedKey) {
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

    @ApiOperation(value = "Place order")
    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    public ResponseResult placeOrder(@ApiParam(required = true,name = "cartEntityId",value="cartEntityId") @RequestParam("cartEntityId") List<Integer> cartEntityIds,
                                    @ApiParam(required = true,name = "remark",value="remark") @RequestParam("remark") String remark) {
        String message="下订单成功 Successfully placed order!!!";

        Integer result=200;
        try {
            List<Long> placedOrderIds = new ArrayList<>();
            for (int cartEntityId : cartEntityIds){
                long orderId = tTotalOrderService.addTTotalOrderEntity(cartEntityId, remark );
                placedOrderIds.add(orderId);
            }
            tShoppingCartService.deleteShoppingCartItemEntity(cartEntityIds);
            httpSession.setMaxInactiveInterval(60*60*24);
            httpSession.setAttribute("placedOrderIds", placedOrderIds);
            return new ResponseResult(result,message,null);
        } catch (Exception e) {
            message="下订单失败 Failed to place order!!!";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "Process orders")
    @RequestMapping(value = "/processOrders", method = RequestMethod.POST)
    public ResponseResult processOrders(@ApiParam(required = true,name = "orderIds",value="orderIds") @RequestParam("orderIds") List<Long> orderIds,
                                        @ApiParam(required = true,name = "logisticsCompanyId",value="logisticsCompanyId") @RequestParam("logisticsCompanyId") int logisticsCompanyId,
                                    @ApiParam(required = true,name = "comment",value="comment") @RequestParam("comment") String comment) {
        String message="处理订单成功 Successfully process order!!!";

        Integer result=200;
        try {
            tLogisticsOrderService.addShippingOrders(orderIds, logisticsCompanyId);
            return new ResponseResult(result,message,null);
        } catch (Exception e) {
            message="处理订单失败 Failed to process order!!!";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

}
