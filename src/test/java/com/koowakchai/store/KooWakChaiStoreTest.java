package com.koowakchai.store;

import com.auth0.jwt.interfaces.Claim;
import com.koowakchai.common.email.AnnexEMailService;
import com.koowakchai.common.util.JWTUtils;
import com.koowakchai.hibernate.entity.TTotalOrderEntity;
import com.koowakchai.store.dao.TLogisticsOrderDao;
import com.koowakchai.store.dao.TTotalOrderDao;
import com.koowakchai.store.service.StoreEmailService;
import com.koowakchai.store.service.TBusinessService;
import com.koowakchai.store.service.TLogisticsOrderService;
import com.koowakchai.store.service.TShoppingCartService;
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



    private JWTUtils jwtUtils;

    @Test
    public void testGetAllBusinessTypes() throws Exception{
        List<String> businessTypeList = tBusinessService.getAllBusinessTypes();
        System.out.println("test hahah: " + businessTypeList.size());
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
}
