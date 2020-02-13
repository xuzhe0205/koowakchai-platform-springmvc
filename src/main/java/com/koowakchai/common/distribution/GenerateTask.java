package com.koowakchai.common.distribution;



import com.koowakchai.errand.dao.TPickupDropoffOrderDao;
import com.koowakchai.errand.dao.TProcurementServiceOrderDao;
import com.koowakchai.hibernate.entity.TPickupDropoffOrderEntity;
import com.koowakchai.hibernate.entity.TProcurementServiceOrderEntity;
import com.koowakchai.hibernate.entity.TTravelOrderEntity;
import com.koowakchai.hibernate.entity.TUserEntity;
import com.koowakchai.travel.service.TAirportRideService;
import com.koowakchai.travel.service.THitchHikeService;
import com.koowakchai.user.service.TUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

@Configuration
public class GenerateTask extends TimerTask {

    @Autowired
    private TUserService tUserService;

    @Autowired
    private THitchHikeService tHitchHikeService;

    @Autowired
    private TAirportRideService tAirportRideService;

    @Autowired
    private TProcurementServiceOrderDao tProcurementServiceOrderDao;

    @Autowired
    private TPickupDropoffOrderDao tPickupDropoffOrderDao;

    private static Logger logger = Logger.getLogger(GenerateTask.class);
//    private ClientService clientService = (ClientService)ServiceUtil.getService("clientService");
    public String checkRole = "";
    public String checkTravelType = "";
    public String errandType = "";

    public String getCheckRole() {
        return checkRole;
    }

    public String getCheckTravelType() {
        return checkTravelType;
    }

    public String getErrandType() {
        return errandType;
    }

    public void setRole(String role) throws Exception{
        this.checkRole=role;
    }

    public void setTravelType(String travelType) throws Exception{
        this.checkTravelType = travelType;
    }

    public void setErrandType(String errandType) throws Exception{
        this.errandType = errandType;
    }

    @Override
    public void run() {
        try {
            if (this.checkRole.equals("driver")){
                //get active drivers
                List<TUserEntity> activeDriverList = tUserService.getDriverByStatus("active");
                if (activeDriverList == null || activeDriverList.size() == 0) {
                    //重新生成名单
                    UserQueue.getInstance().putAll(null);
                    logger.error("There is no active drivers");
                    return;
                }
                Object[] users = UserQueue.getInstance().getAllUsers();
                for (Object user : users) {
                    if (!activeDriverList.contains(user)) {
                        UserQueue.getInstance().remove((TUserEntity) user);
                    }
                }
                //重新生成名单
                UserQueue.getInstance().putAll(activeDriverList);
                //生成任务
                List<TTravelOrderEntity> tTravelOrderEntityList = new ArrayList<>();
                if (checkTravelType.equals("airport ride")){
                    tTravelOrderEntityList = tAirportRideService.getAirportRideOrder();
                }
                else{
                    tTravelOrderEntityList = tHitchHikeService.getHitchHikeOrder();
                }
                TaskQueue.getInstance().putAll(tTravelOrderEntityList);
            }
            else{
                List<TUserEntity> activeDeliverymanList = tUserService.getDeliverymanByStatus("active");
                if (activeDeliverymanList == null || activeDeliverymanList.size() == 0) {
                    //重新生成名单
                    UserQueue.getInstance().putAll(null);
                    logger.error("There is no active deliveryman");
                    return;
                }
                Object[] users = UserQueue.getInstance().getAllUsers();
                for (Object user : users) {
                    if (!activeDeliverymanList.contains(user)) {
                        UserQueue.getInstance().remove((TUserEntity) user);
                    }
                }
                //重新生成名单
                UserQueue.getInstance().putAll(activeDeliverymanList);
                //生成任务

                if (errandType.equals("procurement service")){
                    List<TProcurementServiceOrderEntity> tProcurementServiceOrderEntityList = tProcurementServiceOrderDao.getTProcurementServiceOrderList();
                    TaskQueue.getInstance().putAll(tProcurementServiceOrderEntityList);
                }
                else{
                    List<TPickupDropoffOrderEntity> tPickupDropoffOrderEntityList= tPickupDropoffOrderDao.getPickupDropoffOrder();
                    TaskQueue.getInstance().putAll(tPickupDropoffOrderEntityList);
                }
            }
        }
        catch (Throwable t) {
            logger.error("自动派单任务异常!", t);
        }
        finally {
            logger.info("此次任务执行完毕!");
        }
    }
}
