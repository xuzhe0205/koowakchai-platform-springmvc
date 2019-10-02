package com.koowakchai.store;

import com.koowakchai.store.dao.TBusinessDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/applicationContext.xml"})
@WebAppConfiguration
public class KooWakChaiStoreTest {

    @Autowired
    private TBusinessDao tBusinessDao;

    @Test
    public void testGetAllBusinessTypes() throws Exception{
        List<String> businessTypeList = tBusinessDao.getAllBusinessTypes();
        System.out.println("test hahah: " + businessTypeList.size());
    }
}
