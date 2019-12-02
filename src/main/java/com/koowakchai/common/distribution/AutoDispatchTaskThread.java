package com.koowakchai.common.distribution;

import com.koowakchai.errand.dao.TDeliverymanDao;
import com.koowakchai.errand.service.TPickupDropoffOrderService;
import com.koowakchai.errand.service.TProcurementServiceOrderService;
import com.koowakchai.hibernate.entity.TPickupDropoffOrderEntity;
import com.koowakchai.hibernate.entity.TProcurementServiceOrderEntity;
import com.koowakchai.hibernate.entity.TTravelOrderEntity;
import com.koowakchai.hibernate.entity.TUserEntity;
import com.koowakchai.travel.dao.TDriverDao;
import com.koowakchai.travel.service.TAirportRideService;
import com.koowakchai.travel.service.THitchHikeService;
import com.koowakchai.user.dao.TUserDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AutoDispatchTaskThread implements Runnable {
    private static Logger logger = Logger.getLogger(AutoDispatchTaskThread.class);

    private boolean stop = false;


    @Autowired
    private TAirportRideService tAirportRideService;

    @Autowired
    private THitchHikeService tHitchHikeService;

    @Autowired
    private TProcurementServiceOrderService tProcurementServiceOrderService;

    @Autowired
    private TPickupDropoffOrderService tPickupDropoffOrderService;

    @Autowired
    private TDeliverymanDao tDeliverymanDao;

    @Autowired
    private TDriverDao tDriverDao;

    @Autowired
    private GenerateTask generateTask;

    @Autowired
    private TUserDao tUserDao;

    @Override
    public void run() {
        try{
            while(!stop){

                generateTask.run();
                if (generateTask.getCheckRole().equals("driver")){
                    // the driver entity
                    TUserEntity tUserEntity = UserQueue.getInstance().takeNext();
                    Object[] objects = TaskQueue.getInstance().takeAll();
                    List<TTravelOrderEntity> tTravelOrderEntityList = new ArrayList<>();

                    for (Object tTravelOrderEntity : objects){

                        long travelOrderId = ((TTravelOrderEntity)tTravelOrderEntity).getId();
                        long passengerId = ((TTravelOrderEntity)tTravelOrderEntity).getPassengerId();
                        TUserEntity customerEntity = tUserDao.getTUserEntity(passengerId);
                        String customerRegion = customerEntity.getRegion();
                        String driverRegion = tUserEntity.getRegion();
                        long driverId = tUserEntity.getId();
                        if (((TTravelOrderEntity)tTravelOrderEntity).getDriverId()==null){
                            if (customerRegion.equals(driverRegion)){
                                if (((TTravelOrderEntity)tTravelOrderEntity).getTravelType().equals("airport ride")){
                                    tAirportRideService.assignAirportRideOrder(driverId, travelOrderId);
                                }
                                else{
                                    tHitchHikeService.assignHitchHikeOrder(driverId, travelOrderId);
                                }
                                tDriverDao.updateTDriverInfoEntity(driverId, travelOrderId);
                                break;
                            }
                        }


                    }
                    UserQueue.getInstance().remove(tUserEntity);
                    if (UserQueue.getInstance().getAllUsers().length==0){
                        stop = true;
                    }

                }
                else{
                    if (generateTask.getErrandType().equals("procurement service")){
                        TProcurementServiceOrderEntity tProcurementServiceOrderEntity = (TProcurementServiceOrderEntity) TaskQueue.getInstance().take();
                        int procurementOrderId = tProcurementServiceOrderEntity.getId();
                        long customerId = tProcurementServiceOrderEntity.getCustomerId();
                        TUserEntity tUserEntity = UserQueue.getInstance().takeNext();
                        long deliverymanId = tUserEntity.getId();

                        tDeliverymanDao.assignProcurementServiceOrder(deliverymanId, procurementOrderId, "procurement service");
                    }
                    else{
                        TPickupDropoffOrderEntity tPickupDropoffOrderEntity = (TPickupDropoffOrderEntity)TaskQueue.getInstance().take();
                        int pickupDropoffOrderId = tPickupDropoffOrderEntity.getId();
                        long customerId = tPickupDropoffOrderEntity.getCustomerId();
                        TUserEntity tUserEntity = UserQueue.getInstance().takeNext();
                        long deliverymanId = tUserEntity.getId();

                        tDeliverymanDao.assignProcurementServiceOrder(deliverymanId, pickupDropoffOrderId, "pickup dropoff");

                    }
                }

            }
        }
        catch(Throwable t){
            logger.error(t.getMessage(),t);
        }
    }
}
