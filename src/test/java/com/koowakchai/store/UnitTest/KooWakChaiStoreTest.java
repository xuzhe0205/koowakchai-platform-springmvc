package com.koowakchai.store.UnitTest;

import com.auth0.jwt.interfaces.Claim;
import com.koowakchai.common.distribution.AutoDispatchTaskThread;
import com.koowakchai.common.distribution.GenerateTask;
import com.koowakchai.common.email.AnnexEMailService;
import com.koowakchai.common.util.JWTUtils;
import com.koowakchai.hibernate.entity.*;
import com.koowakchai.store.dao.TECigaretteDao;
import com.koowakchai.store.dao.TLogisticsOrderDao;
import com.koowakchai.store.dao.TTotalOrderDao;
import com.koowakchai.store.service.StoreEmailService;
import com.koowakchai.store.service.TBusinessService;
import com.koowakchai.store.service.TLogisticsOrderService;
import com.koowakchai.store.service.TShoppingCartService;
import com.koowakchai.travel.dao.TAirportRideDao;
import com.koowakchai.travel.dao.TDriverDao;
import com.koowakchai.travel.dao.TTravelOrderDao;
import com.koowakchai.user.dao.TUserDao;
import com.koowakchai.user.service.TUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/applicationContext.xml"})
@WebAppConfiguration
public class KooWakChaiStoreTest {

    @Autowired
    private TBusinessService tBusinessService;

    @Autowired
    private TUserService tUserService;

    @Autowired
    private TUserDao tUserDao;

    @Autowired
    private TShoppingCartService tShoppingCartService;

    @Autowired
    private AnnexEMailService annexEMailService;

    @Autowired
    private TTotalOrderDao tTotalOrderDao;

    @Autowired
    private StoreEmailService storeEmailService;

    @Autowired
    private TLogisticsOrderService tLogisticsOrderService;

    @Autowired
    private TAirportRideDao tAirportRideDao;

    @Autowired
    private TDriverDao tDriverDao;

    @Autowired
    private TTravelOrderDao tTravelOrderDao;

    @Autowired
    private AutoDispatchTaskThread autoDispatchTaskThread;

    @Autowired
    private GenerateTask generateTask;

    private JWTUtils jwtUtils;

    @Autowired
    private TECigaretteDao teCigaretteDao;

    @Test
    public void testGetAllBusinessTypes() throws Exception{
        List<String> businessTypeList = tBusinessService.getAllBusinessTypes();
        System.out.println("test hahah: " + businessTypeList.size());
    }

    @Test
    public void testGetUsers() throws Exception{
        Long id = tUserDao.getUserId("oliverxu0205@gmail.com");
        System.out.println("test hahah: " + id);
    }

    @Test
    public void testLoginToken() throws Exception{
        List<Object> userInfoEntity = tUserService.getUserInfo("xuzhe3", "950205", "driver");
        String token = "";
        if (userInfoEntity != null) {
            Object[] resultUserInfo = (Object[]) userInfoEntity.get(0);
            Long userId = Long.parseLong(String.valueOf(resultUserInfo[0]));
            token = JWTUtils.createToken(userId);
            Map<String, Claim> claims = jwtUtils.verifyToken(token);
        }

    }

    @Test
    public void testGetUserId() throws Exception{
        Long userId = tUserDao.getUserId("oliverxu0205@gmail.com");
        System.out.println("your userId: " + userId);
    }

    @Test
    public void testShoppingCartItems() throws Exception{
        int cartSize = tShoppingCartService.getCartItem(1).size();
        System.out.println("my shopping cart size: " + cartSize);
    }

    @Test
    public void testEmail() throws Exception{
//        List<Object> objectList = new ArrayList<>();
//        TTotalOrderEntity tTotalOrderEntity = tTotalOrderDao.getTTotalOrderEntity(2);
//        objectList.add(tTotalOrderEntity);
        List<Long> orderIds = new ArrayList<>();
        long l1 = 25;
        long l2= 26;
        orderIds.add(l1);
        orderIds.add(l2);
//        TTotalOrderEntity tTotalOrderEntity = tTotalOrderDao.getTTotalOrderEntity(l1);
        System.out.println("sent email: " + storeEmailService.sendConfirmationEmail(orderIds));

    }

    @Test
    public void testLogisticsOrder() throws Exception{
        List<Long> orderIds = new ArrayList<>();
        orderIds.add(new Long(25));
        orderIds.add(new Long(26));
        tLogisticsOrderService.addShippingOrders(orderIds,1);

    }

    @Test
    public void testGetTAirportInfoByCondition() throws Exception{
        List<TAirportInfoEntity> tAirportInfoEntityList = tAirportRideDao.getTAirportEntityByCondition("", "");
        System.out.println("airportinfo number: " + tAirportInfoEntityList.size());

    }

    @Test
    public void testGetDriverByStatus() throws Exception{
        List<TUserEntity> tUserEntityList = tUserService.getDriverByStatus("active");
        System.out.println("how many active driver: " + tUserEntityList.size());
    }

    @Test
    public void testGetTravelOrderByCondition() throws Exception{
        List<TTravelOrderEntity> tTravelOrderEntityList = tAirportRideDao.getTravelOrderEntityByType("hitch hike");
        TDriverEntity tDriverEntity = tDriverDao.getTDriverEntity(28);
        tDriverEntity.setAssignedTripId((long)1);
        System.out.println("how many: " + tTravelOrderEntityList.size());
    }

    @Test
    public void testPushOrder() throws Exception{
        TTravelOrderEntity tTravelOrderEntity = tTravelOrderDao.getTravelOrders(28);
        System.out.println("how many: ");
    }

    @Test
    public void testAutoAssign() throws Exception{
        generateTask.setRole("deliveryman");

        generateTask.setErrandType("pickup dropoff");
        autoDispatchTaskThread.run();
        System.out.println("how many: ");
    }

    @Test
    public void testECigaPagination() throws Exception{
        List<TECigaretteEntity> teCigaretteEntityList = teCigaretteDao.getTEcigaEntitySorted("salesVol", 3, 6);
        System.out.println(teCigaretteEntityList.size());
    }


}
