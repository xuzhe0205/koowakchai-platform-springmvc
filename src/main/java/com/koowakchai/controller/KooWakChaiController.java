package com.koowakchai.controller;

import com.koowakchai.common.base.ResponseResult;
import com.koowakchai.store.service.TBusinessService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/")
@RestController
public class KooWakChaiController {

    @Autowired
    private TBusinessService tBusinessService;

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
