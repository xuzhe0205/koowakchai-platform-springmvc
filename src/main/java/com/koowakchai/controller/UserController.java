package com.koowakchai.controller;

import com.koowakchai.common.base.ResponseResult;
import com.koowakchai.common.util.JWTUtils;
import com.koowakchai.store.service.StoreEmailService;
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
            if (userInfoEntity != null){
                Object[] resultUserInfo = (Object[]) userInfoEntity.get(0);
                Long userId = Long.parseLong(String.valueOf(resultUserInfo[0]));
                token = JWTUtils.createToken(userId);

                return new ResponseResult(result,message,token);
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
                                @ApiParam(required = true,name = "dob",value="user's dob") @RequestParam("dob") String dob
                                ) {
        String message="注册成功！！！";

        Integer result=200;

        String token = "";
        try {
            if (confirmPassword.equals(password)){
                tUserService.addTUserEntity(username,password,email,dob);
                tUserService.saveOrUpdateTUserRole(roleName,email);
            }
            else{
                result = 500;
                message = "Unmatched password";
            }
            return new ResponseResult(result, message, null);

        } catch (Exception e) {
            message="注册失败！！！";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "User add/update address")
    @RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
    public ResponseResult updateAddress(@ApiParam(required = true,name = "recipientPhone",value="Recipient phone number") @RequestParam("recipientPhone") String recipientPhone,
                                @ApiParam(required = true,name = "recipientName",value="Recipient Name") @RequestParam("recipientName") String recipientName,
                                        @ApiParam(required = true,name = "userId",value="buyer's userId") @RequestParam("userId") long userId,
                                @ApiParam(required = true,name = "fullAddr",value="recipient full address") @RequestParam("fullAddr") String fullAddr
    ) {
        String message="成功更新用户地址Successfully update address！！！";

        Integer result=200;

        try {
            tAddressBookService.saveOrUpdateAddress(userId, recipientName, recipientPhone,fullAddr);
            return new ResponseResult(result, message, null);

        } catch (Exception e) {
            message="更新用户地址失败 Fail to update address！！！";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }


    @ApiOperation(value = "Update user info")
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public ResponseResult updateUserInfo(@ApiParam(required = true,name = "userId",value="User's Id") @RequestParam("userId") long userId,
                                        @ApiParam(required = true,name = "userPhone",value="User's phone number") @RequestParam("userPhone") String userPhone,
                                        @ApiParam(required = true,name = "gender",value="User's gender") @RequestParam("gender") String gender,
                                        @ApiParam(required = true,name = "userUrl",value="User's URL") @RequestParam("userUrl") String userUrl
    ) {
        String message="成功更新用户个人信息Successfully update user info！！！";

        Integer result=200;

        try {
            tUserService.saveOrUpdateTUserEntity(userId,userUrl,gender,userPhone);
            return new ResponseResult(result, message, null);

        } catch (Exception e) {
            message="更新用户个人信息 Fail to update user info";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "Add/Update payment info")
    @RequestMapping(value = "/updatePaymentInfo", method = RequestMethod.POST)
    public ResponseResult updatePaymentInfo(@ApiParam(required = true,name = "userId",value="User's Id") @RequestParam("userId") long userId,
                                            @ApiParam(required = true,name = "method",value="User's payment method") @RequestParam("method") String method,
                                            @ApiParam(required = true,name = "cardNum",value="User's Card Number") @RequestParam("cardNum") String cardNum,
                                            @ApiParam(required = true,name = "zipcode",value="User's payment's zipcode") @RequestParam("zipcode") String zipcode,
                                            @ApiParam(required = true,name = "cvv",value="User's card's cvv") @RequestParam("cvv") String cvv,
                                            @ApiParam(required = true,name = "expDate",value="User's card's expDate") @RequestParam("expDate") String expDate
    ) {
        String message="成功更新用户支付方式信息Successfully update/add user's payment method！！！";

        Integer result=200;

        try {
            tPaymentInfoService.addTPaymentInfoEntity(userId, method, cardNum, zipcode, cvv, expDate);
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
    public ResponseResult updatePaymentInfo(@ApiParam(required = false,name="Authorization")  @RequestHeader(value = "Authorization",required = false,defaultValue = "") String Authorization,
                                            @ApiParam(required = false,name = "orderId",value="orderId") @RequestParam(value = "orderId", defaultValue = "0")long orderId,
                                            @ApiParam(required = true,name = "userId",value="User's Id") @RequestParam("userId") long userId,
                                            @ApiParam(required = true,name = "paymentId",value="User's paymentId") @RequestParam("paymentId") long paymentId,
                                            @ApiParam(required = true,name = "addrId",value="User's addrId") @RequestParam("addrId") long addrId
    ) {
        String message="成功更新用户订单Successfully update user's placed orders！！！";

        Integer result=200;

        List<Long> emailOrderIds= new ArrayList<>();

        try {

            List<Long> placedOrderIds = (ArrayList<Long>)httpSession.getAttribute("placedOrderIds");
            if (placedOrderIds == null){
                completeOrderService.updateTTotalOrderEntity(orderId, userId, addrId, paymentId);

                emailOrderIds.add(orderId);
            }
            else{
                emailOrderIds = placedOrderIds;
                for (Long id : placedOrderIds){
                    completeOrderService.updateTTotalOrderEntity(id, userId, addrId, paymentId);
                }
            }

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
}

