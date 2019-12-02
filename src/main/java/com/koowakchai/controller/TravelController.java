package com.koowakchai.controller;

import com.koowakchai.common.base.ResponseResult;
import com.koowakchai.hibernate.entity.TAirportInfoEntity;
import com.koowakchai.travel.dao.TTravelOrderDao;
import com.koowakchai.travel.service.TAirportRideService;
import com.koowakchai.travel.service.TDriverService;
import com.koowakchai.travel.service.THitchHikeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RequestMapping("/travel")
@RestController
@EnableAsync
public class TravelController {

    @Autowired
    private THitchHikeService tHitchHikeService;

    @Autowired
    private TDriverService tDriverService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private TAirportRideService tAirportRideService;

    @Autowired
    private TTravelOrderDao tTravelOrderDao;

    @ApiOperation(value = "Add travel-hitchhiking order")
    @RequestMapping(value = "/addHitchHikingOrder", method = RequestMethod.POST)
    public ResponseResult addHitchHikingOrder(@ApiParam(required = true,name = "passengerId",value="passengerId") @RequestParam("passengerId") long passengerId,
                                            @ApiParam(required = true,name = "pickupTime",value="pickupTime") @RequestParam("pickupTime") String pickupTime,
                                            @ApiParam(required = true,name = "pickupLocation",value="pickupLocation") @RequestParam("pickupLocation") String pickupLocation,
                                            @ApiParam(required = true,name = "dropoffLocation",value="dropoffLocation") @RequestParam("dropoffLocation") String dropoffLocation,
                                            @ApiParam(required = true,name = "numberPassenger",value="numberPassenger") @RequestParam("numberPassenger") int numberPassenger) {
        String message="乘客用户添加顺风车订单成功 Successfully added hitchhiking order!!!";

        Integer result=200;
        try {
            Double price = (Double)httpSession.getAttribute("hitchhiking price");
            tHitchHikeService.addHitchHikeOrderEntity(passengerId, pickupTime, pickupLocation, dropoffLocation, price, numberPassenger);
            return new ResponseResult(result,message,null);
        } catch (Exception e) {
            message="乘客用户添加顺风车订单失败 Failed to add hitchhiking order!!!";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "Get hitchhiking price")
    @RequestMapping(value = "/getHitchHikingPrice", method = RequestMethod.GET)
    public ResponseResult getHitchHikingPrice(@ApiParam(required = true,name = "passengerId",value="passengerId") @RequestParam("passengerId") long passengerId,
                                            @ApiParam(required = true,name = "pickupTime",value="pickupTime") @RequestParam("pickupTime") String pickupTime,
                                            @ApiParam(required = true,name = "pickupLocation",value="pickupLocation") @RequestParam("pickupLocation") String pickupLocation,
                                            @ApiParam(required = true,name = "dropoffLocation",value="dropoffLocation") @RequestParam("dropoffLocation") String dropoffLocation,
                                            @ApiParam(required = true,name = "distance",value="distance") @RequestParam("distance") double distance,
                                            @ApiParam(required = true,name = "numberPassenger",value="numberPassenger") @RequestParam("numberPassenger") int numberPassenger) {
        String message="乘客用户成功查询车费 Successfully get hitchhiking trip price!!!";

        Integer result=200;
        try {
            double price = tHitchHikeService.getHitchHikePrice(passengerId, pickupTime, pickupLocation, dropoffLocation, distance, numberPassenger);
            httpSession.setAttribute("hitchhiking price", price);
            return new ResponseResult(result,message,price);
        } catch (Exception e) {
            message="乘客用户成功查询车费 Failed to get hitchhiking trip price!!!";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "Get driver info")
    @RequestMapping(value = "/getDriverInfo", method = RequestMethod.GET)
    public ResponseResult getDriverInfo(@ApiParam(required = true,name = "driverId",value="driverId") @RequestParam("driverId") long driverId) {
        String message="乘客用户成功查询司机信息 Successfully get driver info!!!";

        Integer result=200;
        try {
            Map<String, String> driverInfo= tDriverService.getDriverInfo(driverId);
            return new ResponseResult(result,message, driverInfo);
        } catch (Exception e) {
            message="乘客用户查询司机信息失败 Failed to get driver info!!!";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "process travel-hitchhiking order")
    @RequestMapping(value = "/processHitchHikingOrder", method = RequestMethod.POST)
    public ResponseResult processHitchHikingOrder(@ApiParam(required = true,name = "passengerId",value="passengerId") @RequestParam("passengerId") long passengerId,
                                                  @ApiParam(required = true,name = "travelOrderId",value="travelOrderId") @RequestParam("travelOrderId") long travelOrderId,
                                                  @ApiParam(required = true,name = "driverId",value="driverId") @RequestParam("driverId") long driverId) {
        String message="司机处理顺风车订单成功 Successfully processed hitchhiking order!!!";

        Integer result=200;
        try {
            tHitchHikeService.processHitchHikingOrder(driverId, passengerId, travelOrderId);
            return new ResponseResult(result,message,null);
        } catch (Exception e) {
            message="司机处理顺风车订单失败 Failed to process hitchhiking order!!!";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "Get airport info")
    @RequestMapping(value = "/getAirportInfo", method = RequestMethod.GET)
    public ResponseResult getAirportInfo(@ApiParam(name = "city",value="city") @RequestParam(value = "city", defaultValue = "") String city,
                                         @ApiParam(name = "airport",value="airport") @RequestParam(value = "airport", defaultValue = "") String airport) {
        String message="成功展示/筛选出机场信息 Successfully get airport info!!!";

        Integer result=200;
        try {
            List<TAirportInfoEntity> tAirportInfoEntityList = tAirportRideService.getAirportInfoByCondition(city, airport);
            return new ResponseResult(result,message, tAirportInfoEntityList);
        } catch (Exception e) {
            message="展示/筛选出机场信息失败 Failed to get airport info!!!";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "Get airport ride price")
    @RequestMapping(value = "/getAirportRidePrice", method = RequestMethod.GET)
    public ResponseResult getAirportRidePrice(@ApiParam(required = true,name = "passengerId",value="passengerId") @RequestParam("passengerId") long passengerId,
                                              @ApiParam(required = true,name = "pickupTime",value="pickupTime") @RequestParam("pickupTime") String pickupTime,
                                              @ApiParam(required = true,name = "pickupLocation",value="pickupLocation") @RequestParam("pickupLocation") String pickupLocation,
                                              @ApiParam(required = true,name = "dropoffLocation",value="dropoffLocation") @RequestParam("dropoffLocation") String dropoffLocation,
                                              @ApiParam(required = true,name = "distance",value="distance") @RequestParam("distance") double distance) {
        String message="乘客用户成功查询车费 Successfully get airportRide trip price!!!";

        Integer result=200;
        try {
            double price = tAirportRideService.getAirportRidePrice(passengerId, pickupTime, pickupLocation, dropoffLocation, distance);
            httpSession.setAttribute("airportRide price", price);
            return new ResponseResult(result,message,price);
        } catch (Exception e) {
            message="乘客用户成功查询车费 Failed to get airportRide trip price!!!";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "Add travel-airportRide order")
    @RequestMapping(value = "/addAirportRideOrder", method = RequestMethod.POST)
    public ResponseResult addAirportRideOrder(@ApiParam(required = true,name = "passengerId",value="passengerId") @RequestParam("passengerId") long passengerId,
                                              @ApiParam(required = true,name = "pickupTime",value="pickupTime") @RequestParam("pickupTime") String pickupTime,
                                              @ApiParam(required = true,name = "pickupLocation",value="pickupLocation") @RequestParam("pickupLocation") String pickupLocation,
                                              @ApiParam(required = true,name = "dropoffLocation",value="dropoffLocation") @RequestParam("dropoffLocation") String dropoffLocation,
                                              @ApiParam(required = true,name = "numberPassenger",value="numberPassenger") @RequestParam("numberPassenger") int numberPassenger,
                                              @ApiParam(required = true,name = "airportInfoId",value="airportInfoId") @RequestParam("airportInfoId") int airportInfoId,
                                              @ApiParam(required = true,name = "flightNumber",value="flightNumber") @RequestParam("flightNumer") String flightNumber) {
        String message="乘客用户添加机场接送订单成功 Successfully added hitchhiking order!!!";

        Integer result=200;
        try {
            Double price = (Double)httpSession.getAttribute("airportRide price");
            tAirportRideService.addAirportRideOrder(airportInfoId, passengerId, pickupTime, pickupLocation, dropoffLocation, price, numberPassenger, flightNumber);
            return new ResponseResult(result,message,null);
        } catch (Exception e) {
            message="乘客用户添加机场接送订单失败 Failed to add hitchhiking order!!!";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "process travel-hitchhiking order")
    @RequestMapping(value = "/processAirportRideOrder", method = RequestMethod.POST)
    public ResponseResult processAirportRideOrder(@ApiParam(required = true,name = "passengerId",value="passengerId") @RequestParam("passengerId") long passengerId,
                                                  @ApiParam(required = true,name = "travelOrderId",value="travelOrderId") @RequestParam("travelOrderId") long travelOrderId,
                                                  @ApiParam(required = true,name = "driverId",value="driverId") @RequestParam("driverId") long driverId) {
        String message="司机处理顺风车订单成功 Successfully processed hitchhiking order!!!";

        Integer result=200;
        try {
            tAirportRideService.processAirportRideOrder(driverId, passengerId, travelOrderId);
            return new ResponseResult(result,message,null);
        } catch (Exception e) {
            message="司机处理顺风车订单失败 Failed to process hitchhiking order!!!";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

    @ApiOperation(value = "push travel order to driver")
    @RequestMapping(value = "/getTravelOrders", method = RequestMethod.POST)
    public ResponseResult getTravelOrders(@ApiParam(required = true,name = "driverId",value="driverId") @RequestParam("driverId") long driverId) {
        String message="给司机推送订单成功 Successfully pushed travel order to driver!!!";

        Integer result=200;
        try {
            tTravelOrderDao.getTravelOrders(driverId);
            return new ResponseResult(result,message,null);
        } catch (Exception e) {
            message="给司机推送订单失败 Failed to push travel order to driver!!!";
            result=500;
            e.printStackTrace();
        }
        return new ResponseResult(result,message,null);
    }

}
