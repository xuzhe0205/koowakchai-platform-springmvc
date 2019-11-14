package com.koowakchai.controller;

import com.koowakchai.common.base.ResponseResult;
import com.koowakchai.common.util.JWTUtils;
import com.koowakchai.user.service.TUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private TUserService tUserService;

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
    public ResponseResult login(@ApiParam(required = true,name = "username",value="user's username") @RequestParam("username") String username,
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
                tUserService.saveOrUpdateTUser(username,password,email,dob);
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

}
