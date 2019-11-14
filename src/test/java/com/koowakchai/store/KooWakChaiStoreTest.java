package com.koowakchai.store;

import com.auth0.jwt.interfaces.Claim;
import com.koowakchai.common.util.JWTUtils;
import com.koowakchai.store.dao.TBusinessDao;
import com.koowakchai.store.service.TBusinessService;
import com.koowakchai.user.dao.TUserDao;
import com.koowakchai.user.service.TUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
}
