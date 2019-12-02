package com.koowakchai.controller;


import com.koowakchai.common.base.ResponseResult;
import com.koowakchai.errand.service.TErrandService;
import com.koowakchai.errand.service.TPickupDropoffOrderService;
import com.koowakchai.errand.service.TProcurementServiceOrderService;
import com.koowakchai.hibernate.entity.TPickupDropoffOrderEntity;
import com.koowakchai.hibernate.entity.TProcurementServiceOrderEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/errand")
@RestController
@EnableAsync
public class ErrandController {

    @Autowired
    private TProcurementServiceOrderService tProcurementServiceOrderService;

    @Autowired
    private TPickupDropoffOrderService tPickupDropoffOrderService;

    @Autowired
    private TErrandService tErrandService;

    @Autowired
    private HttpSession httpSession;


    @ApiOperation(value = "check errand fee")
    @RequestMapping(value = "/checkErrandFee", method = RequestMethod.GET)
    public ResponseResult addProcurementServiceOrder(@ApiParam(required = true,name = "distance",value="distance") @RequestParam("distance") double distance,
                                                     @ApiParam(required = true,name = "itemsWeight",value="itemsWeight") @RequestParam("itemsWeight") double itemsWeight,
                                                     @ApiParam(required = true,name = "errandType",value="errandType") @RequestParam("errandType") String errandType,
                                                     @ApiParam(required = true,name = "deliveryTime",value="deliveryTime") @RequestParam("deliveryTime") String deliveryTime) {
        String message="成功查询跑腿业务价格 Successfully check errand service fee!!!";

        Integer result=200;
        try {
            double deliveryFee = tErrandService.checkErrandFee(distance, itemsWeight, errandType, deliveryTime);
            httpSession.setAttribute("deliveryFee", deliveryFee);
            return new ResponseResult(result,message,null);
        } catch (Exception e) {
            message="查询跑腿业务价格失败 Failed to check errand service fee!!!";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "add procurement service order")
    @RequestMapping(value = "/addProcurementServiceOrder", method = RequestMethod.POST)
    public ResponseResult addProcurementServiceOrder(@ApiParam(required = true,name = "userId",value="userId") @RequestParam("userId") long userId,
                                                     @ApiParam(required = true,name = "deliveryAddress",value="deliveryAddress") @RequestParam("deliveryAddress") String deliveryAddress,
                                                     @ApiParam(required = true,name = "purchaseAddress",value="purchaseAddress") @RequestParam("purchaseAddress") String purchaseAddress,
                                                     @ApiParam(required = true,name = "items",value="items") @RequestParam("items") String items,
                                                     @ApiParam(required = true,name = "deliveryTime",value="deliveryTime") @RequestParam("deliveryTime") String deliveryTime,
                                                     @ApiParam(required = true,name = "tips",value="tips") @RequestParam("tips") double tips,
                                                     @ApiParam(required = true,name = "status",value="status") @RequestParam("status") String status) {
        String message="顾客成功添加代购订单 Successfully added procurement service order!!!";

        Integer result=200;
        try {
            double deliveryFee = (double)httpSession.getAttribute("deliveryFee");
            tProcurementServiceOrderService.addProcurementServiceOrder(userId, deliveryAddress, purchaseAddress, items, deliveryTime, tips, deliveryFee);

            return new ResponseResult(result,message,null);
        } catch (Exception e) {
            message="顾客添加代购订单失败 Failed to add procurement service order!!!";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "add pickup dropoff order")
    @RequestMapping(value = "/addPickupDropoffOrder", method = RequestMethod.POST)
    public ResponseResult addPickupDropoffOrder(@ApiParam(required = true,name = "userId",value="userId") @RequestParam("userId") long userId,
                                                     @ApiParam(required = true,name = "pickupAddress",value="pickupAddress") @RequestParam("pickupAddress") String pickupAddress,
                                                     @ApiParam(required = true,name = "dropoffAddress",value="dropoffAddress") @RequestParam("dropoffAddress") String dropoffAddress,
                                                     @ApiParam(required = true,name = "itemsType",value="itemsType") @RequestParam("itemsType") String itemsType,
                                                     @ApiParam(required = true,name = "itemsWeight",value="itemsWeight") @RequestParam("itemsWeight") double itemsWeight,
                                                     @ApiParam(required = true,name = "deliveryTime",value="deliveryTime") @RequestParam("deliveryTime") String deliveryTime,
                                                     @ApiParam(required = true,name = "tips",value="tips") @RequestParam("tips") double tips,
                                                     @ApiParam(required = true,name = "status",value="status") @RequestParam("status") String status,
                                                     @ApiParam(required = true,name = "remarks",value="remarks") @RequestParam("remarks") String remarks) {
        String message="顾客成功添加代购订单 Successfully added procurement service order!!!";

        Integer result=200;
        try {
            double deliveryFee = (double)httpSession.getAttribute("deliveryFee");

            tPickupDropoffOrderService.addPickupDropoffOrder(userId, pickupAddress, dropoffAddress, itemsType, itemsWeight, tips, remarks, deliveryFee, deliveryTime);

            return new ResponseResult(result,message,null);
        } catch (Exception e) {
            message="顾客添加代购订单失败 Failed to add procurement service order!!!";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }


    @ApiOperation(value = "processProcurementServiceOrder")
    @RequestMapping(value = "/processProcurementServiceOrder", method = RequestMethod.POST)
    public ResponseResult processProcurementServiceOrder(@ApiParam(required = true,name = "userId",value="userId") @RequestParam("userId") long userId,
                                                         @ApiParam(required = true,name = "procurementOrderId",value="procurementOrderId") @RequestParam("procurementOrderId") int procurementOrderId,
                                                         @ApiParam(required = true,name = "itemsPrice",value="itemsPrice") @RequestParam("itemsPrice") double itemsPrice) {
        String message="跑腿代购成功处理代购订单 Successfully processed procurement service order!!!";

        Integer result=200;
        try {
            tProcurementServiceOrderService.updateProcurementServiceOrder(userId, itemsPrice, procurementOrderId);
            return new ResponseResult(result,message,null);
        } catch (Exception e) {
            message="跑腿代购处理代购订单失败 Failed to process procurement service order!!!";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "processPickupDropoffOrder")
    @RequestMapping(value = "/processPickupDropoffOrder", method = RequestMethod.POST)
    public ResponseResult processPickupDropoffOrder(@ApiParam(required = true,name = "userId",value="userId") @RequestParam("userId") long userId,
                                                         @ApiParam(required = true,name = "pickupDropoffOrderId",value="pickupDropoffOrderId") @RequestParam("pickupDropoffOrderId") int pickupDropoffOrderId,
                                                         @ApiParam(required = true,name = "itemsPrice",value="itemsPrice") @RequestParam("itemsPrice") double itemsPrice) {
        String message="跑腿取送货成功处理代购订单 Successfully processed pickup-dropoff order!!!";

        Integer result=200;
        try {
            tPickupDropoffOrderService.updatePickupDropoffOrder(userId, pickupDropoffOrderId);
            return new ResponseResult(result,message,null);
        } catch (Exception e) {
            message="跑腿取送货处理代购订单失败 Fail to process pickup-dropoff order!!!";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "push travel order to deliveryman")
    @RequestMapping(value = "/getErrandOrders", method = RequestMethod.GET)
    public ResponseResult getErrandOrders(@ApiParam(required = true,name = "deliverymanId",value="deliverymanId") @RequestParam("deliverymanId") long deliverymanId) {
        String message="给跑腿员推送订单成功 Successfully pushed errand orders to deliveryman!!!";

        Integer result=200;
        Map<String, Object> errandOrdersMap = new HashMap<>();
        try {
            TProcurementServiceOrderEntity tProcurementServiceOrderEntity = tProcurementServiceOrderService.getAssignedProcurementOrder(deliverymanId);
            TPickupDropoffOrderEntity tPickupDropoffOrderEntity  =  tPickupDropoffOrderService.getAssignedPickupDropoffOrder(deliverymanId);
            errandOrdersMap.put("procurement service", tProcurementServiceOrderEntity);
            errandOrdersMap.put("pickup dropoff", tPickupDropoffOrderEntity);
            return new ResponseResult(result,message,errandOrdersMap);
        } catch (Exception e) {
            message="给跑腿员推送订单失败 Failed to push travel order to deliveryman!!!";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }
}
