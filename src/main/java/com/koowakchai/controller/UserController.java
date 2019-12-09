package com.koowakchai.controller;

import com.auth0.jwt.interfaces.Claim;
import com.koowakchai.common.base.ResponseResult;
import com.koowakchai.common.util.JWTUtils;
import com.koowakchai.errand.service.TDeliverymanService;
import com.koowakchai.hibernate.entity.TPaymentInfoEntity;
import com.koowakchai.hibernate.entity.TUserEntity;
import com.koowakchai.store.service.StoreEmailService;
import com.koowakchai.travel.service.TDriverService;
import com.koowakchai.user.dao.TPaymentInfoDao;
import com.koowakchai.user.dao.TUserDao;
import com.koowakchai.user.service.CompleteOrderService;
import com.koowakchai.user.service.TAddressBookService;
import com.koowakchai.user.service.TPaymentInfoService;
import com.koowakchai.user.service.TUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 100000)
@RequestMapping("/user")
@RestController
@EnableAsync
public class UserController {

    @Autowired
    private TUserService tUserService;

    @Autowired
    private TAddressBookService tAddressBookService;

    @Autowired
    private TPaymentInfoService tPaymentInfoService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private CompleteOrderService completeOrderService;

    @Autowired
    private StoreEmailService storeEmailService;

    @Autowired
    private TDriverService tDriverService;

    @Autowired
    private TDeliverymanService tDeliverymanService;

    @Autowired
    private TUserDao tUserDao;

    @Autowired
    private TPaymentInfoDao tPaymentInfoDao;

    private JWTUtils jwtUtils;

    @ApiOperation(value = "User login")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult login(@ApiParam(required = true,name = "username",value="user's username") @RequestParam("username") String username,
                                @ApiParam(required = true,name = "password",value="user's password") @RequestParam("password") String password,
                                @ApiParam(required = true,name = "roleName",value="user's role") @RequestParam("roleName") String roleName) {
        String message="登陆成功！！！";

        Integer result=200;

        String token = "";
        try {
            List<Object> userInfoEntity = tUserService.getUserInfo(username, password, roleName);
            List<Object> resList = new ArrayList<>();
            if (userInfoEntity != null){
                Object[] resultUserInfo = (Object[]) userInfoEntity.get(0);
                Long userId = Long.parseLong(String.valueOf(resultUserInfo[0]));
                TUserEntity tUserEntity = tUserDao.getTUserEntity(userId);
                token = JWTUtils.createToken(userId);
                resList.add(token);
                resList.add(tUserEntity);
                return new ResponseResult(result,message, resList);
            }
        } catch (Exception e) {
            message="登陆失败！！！";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "User signup")
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseResult signup(@ApiParam(required = true,name = "username",value="user's username") @RequestParam("username") String username,
                                @ApiParam(required = true,name = "password",value="user's password") @RequestParam("password") String password,
                                @ApiParam(required = true,name = "confirmPassword",value="user's confirmPassword") @RequestParam("confirmPassword") String confirmPassword,
                                @ApiParam(required = true,name = "email",value="user's email") @RequestParam("email") String email,
                                @ApiParam(required = true,name = "roleName",value="user's role") @RequestParam("roleName") String roleName,
                                @ApiParam(required = true,name = "dob",value="user's dob") @RequestParam("dob") String dob,
                                 @ApiParam(required = true,name = "gender",value="user's gender") @RequestParam("gender") String gender
                                ) {
        String message="注册成功！！！";

        Integer result=200;

        String token = "";
        Long userId = null;
        try {
            if (confirmPassword.equals(password)){
                userId = tUserService.addTUserEntity(username,password,email,dob, gender);
                tUserService.saveOrUpdateTUserRole(roleName,email);
                if (roleName.equals("Deliveryman")){
                    tDeliverymanService.addTDeliverymanEntity(userId);
                }

            }
            else{
                result = 500;
                message = "Unmatched password";
            }
            return new ResponseResult(result, message, userId);

        } catch (Exception e) {
            message="注册失败！！！";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "User add/update address")
    @RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
    public ResponseResult updateAddress(@ApiParam(required = true,name = "authorization",value="authorization") @RequestParam("authorization") String authorization,
                                        @ApiParam(required = true,name = "recipientPhone",value="Recipient phone number") @RequestParam("recipientPhone") String recipientPhone,
                                        @ApiParam(required = true,name = "recipientName",value="Recipient Name") @RequestParam("recipientName") String recipientName,
                                        @ApiParam(required = true,name = "fullAddr",value="recipient full address") @RequestParam("fullAddr") String fullAddr
    ) {
        String message="成功更新用户地址Successfully update address！！！";

        Integer result=200;

        try {
            Map<String, Claim> claims = jwtUtils.verifyToken(authorization);
            long userId = Long.parseLong(claims.get("userId").asString());
            long addrId = tAddressBookService.saveOrUpdateAddress(userId, recipientName, recipientPhone,fullAddr);
            return new ResponseResult(result, message, addrId);

        } catch (Exception e) {
            message="更新用户地址失败 Fail to update address！！！";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }


    @ApiOperation(value = "Update user info")
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public ResponseResult updateUserInfo(@ApiParam(required = true,name = "authorization",value="authorization") @RequestParam("authorization") String authorization,
                                        @ApiParam(required = true,name = "userPhone",value="User's phone number") @RequestParam("userPhone") String userPhone,
                                         @ApiParam(required = true,name = "email",value="User's email") @RequestParam("email") String email,
                                        @ApiParam(required = true,name = "userUrl",value="User's URL") @RequestParam("userUrl") String userUrl
    ) {
        String message="成功更新用户个人信息Successfully update user info！！！";

        Integer result=200;

        try {
            Map<String, Claim> claims = jwtUtils.verifyToken(authorization);
            long userId = Long.parseLong(claims.get("userId").asString());
            tUserService.saveOrUpdateTUserEntity(userId,userUrl, email, userPhone);
            return new ResponseResult(result, message, null);

        } catch (Exception e) {
            message="更新用户个人信息 Fail to update user info";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "getUserPaymentList")
    @RequestMapping(value = "/getUserPaymentList", method = RequestMethod.GET)
    public ResponseResult getUserPaymentList(@ApiParam(required = true,name = "authorization",value="authorization") @RequestParam("authorization") String authorization) {
        String message="Successfully get this user's payment info list！！！";

        Integer result=200;

        String token = "";
        try {
            Map<String, Claim> claims = jwtUtils.verifyToken(authorization);
            long userId = Long.parseLong(claims.get("userId").asString());
            List<TPaymentInfoEntity> tPaymentInfoEntityList = tPaymentInfoDao.getPaymentInfoList(userId);

            return new ResponseResult(result,message, tPaymentInfoEntityList);

        } catch (Exception e) {
            message ="Fail to get this user's payment info list！！！";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "Add/Update payment info")
    @RequestMapping(value = "/updatePaymentInfo", method = RequestMethod.POST)
    public ResponseResult updatePaymentInfo(@ApiParam(required = true,name = "authorization",value="authorization") @RequestParam("authorization") String authorization,
                                            @ApiParam(required = true,name = "cardholderName",value="cardholderName") @RequestParam("cardholderName") String cardholderName,
                                            @ApiParam(required = true,name = "cardNum",value="User's Card Number") @RequestParam("cardNum") String cardNum,
                                            @ApiParam(required = true,name = "zipcode",value="User's payment's zipcode") @RequestParam("zipcode") String zipcode,
                                            @ApiParam(required = true,name = "cvv",value="User's card's cvv") @RequestParam("cvv") String cvv,
                                            @ApiParam(required = true,name = "expDate",value="User's card's expDate") @RequestParam("expDate") String expDate
    ) {
        String message="成功更新用户支付方式信息Successfully update/add user's payment method！！！";

        Integer result=200;

        try {
            Map<String, Claim> claims = jwtUtils.verifyToken(authorization);
            long userId = Long.parseLong(claims.get("userId").asString());
            tPaymentInfoService.addTPaymentInfoEntity(userId, cardholderName, cardNum, zipcode, cvv, expDate);
            return new ResponseResult(result, message, null);

        } catch (Exception e) {
            message="更新用户支付方式信息Successfully update/add user's payment method！！！";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "Update Placed Orders")
    @RequestMapping(value = "/updateOrders", method = RequestMethod.POST)
    public ResponseResult updateOrders(@ApiParam(required = false,name="Authorization")  @RequestHeader(value = "Authorization",required = false,defaultValue = "") String authorization,
                                            @ApiParam(required = true,name = "orderIds",value="orderIds") @RequestParam(value = "orderIds")List<Long> orderIds,
                                            @ApiParam(required = true,name = "paymentId",value="User's paymentId") @RequestParam("paymentId") long paymentId,
                                            @ApiParam(required = true,name = "addrId",value="User's addrId") @RequestParam("addrId") long addrId
    ) {
        String message="成功更新用户订单Successfully update user's placed orders！！！";

        Integer result=200;

        List<Long> emailOrderIds= new ArrayList<>();

        try {
            Map<String, Claim> claims = jwtUtils.verifyToken(authorization);
            long userId = Long.parseLong(claims.get("userId").asString());
//            List<Long> placedOrderIds = (ArrayList<Long>)httpSession.getAttribute("placedOrderIds");
//            if (placedOrderIds == null){
//
//                completeOrderService.updateTTotalOrderEntity(orderId, userId, addrId, paymentId);
//
//                emailOrderIds.add(orderId);
//            }
//            else{
//                emailOrderIds = placedOrderIds;
//                for (Long id : placedOrderIds){
//                    completeOrderService.updateTTotalOrderEntity(id, userId, addrId, paymentId);
//                }
//            }
            emailOrderIds = orderIds;
            for (Long id : orderIds){
                completeOrderService.updateTTotalOrderEntity(id, userId, addrId, paymentId);
            }
            httpSession.setAttribute("placedOrderIds", null);

            System.out.println("Order placed, now trying to send confirmation email");
            boolean isEmailSent = (storeEmailService.sendConfirmationEmail(emailOrderIds) == null)?false:true;

            return new ResponseResult(result, message, null);

        } catch (Exception e) {
            message="更新用户订单失败 Fail to update user's placed orders！！！";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "Driver info register")
    @RequestMapping(value = "/registerDriverInfo", method = RequestMethod.POST)
    public ResponseResult registerDriverInfo(@ApiParam(required = true,name = "userId",value="userId") @RequestParam("userId") long userId,
                                             @ApiParam(required = true,name = "driverLicense",value="driverLicense") @RequestParam("driverLicense") String driverLicense,
                                             @ApiParam(required = true,name = "vehicleMake",value="vehicleMake") @RequestParam("vehicleMake") String vehicleMake,
                                             @ApiParam(required = true,name = "vehicleModel",value="vehicleModel") @RequestParam("vehicleModel") String vehicleModel,
                                             @ApiParam(required = true,name = "vehicleYear",value="vehicleYear") @RequestParam("vehicleYear") String vehicleYear,
                                             @ApiParam(required = true,name = "vehicleClass",value="vehicleClass") @RequestParam("vehicleClass") String vehicleClass) {
        String message="乘客用户添加顺风车订单成功 Successfully added hitchhiking order!!!";

        Integer result=200;
        try {
            tDriverService.addDriverInfo(userId, driverLicense, vehicleMake, vehicleModel, vehicleYear, vehicleClass);
            return new ResponseResult(result,message,null);
        } catch (Exception e) {
            message="乘客用户添加顺风车订单失败 Failed to add hitchhiking order!!!";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "update user region")
    @RequestMapping(value = "/updateUserRegion", method = RequestMethod.POST)
    public ResponseResult updateUserRegion(@ApiParam(required = true,name = "userId",value="userId") @RequestParam("userId") long userId,
                                             @ApiParam(required = true,name = "region",value="region") @RequestParam("region") String region) {
        String message="用户成功更新（选择） Successfully updated (choose) region!!!";

        Integer result=200;
        try {
            tUserService.updateUserRegion(userId, region);
            return new ResponseResult(result,message,null);
        } catch (Exception e) {
            message="用户更新（选择）失败 Failed to update (choose) region!!!";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }
}

