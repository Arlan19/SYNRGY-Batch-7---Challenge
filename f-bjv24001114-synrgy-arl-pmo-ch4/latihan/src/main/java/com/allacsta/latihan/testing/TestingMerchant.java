package com.allacsta.latihan.testing;

import com.allacsta.latihan.entity.Merchant;
import com.allacsta.latihan.service.MerchantService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestingMerchant {

    @Autowired
    public MerchantService merchantService;

    @Test
    public void saveMerchant(){
        Merchant save = new Merchant();
        save.setMerchant_name("JCO");
        save.setMerchant_location("Jalan Soebrantas");
        save.setOpen(true);

        Map map =  merchantService.save(save);
        int responseCode = (Integer) map.get("status");
        Assert.assertEquals(200, responseCode);
    }

    @Test
    public void getPagination(){
        Map map =  merchantService.pagination(0,10);
        System.out.println(map);
        int responseCode = (Integer) map.get("status");
        Assert.assertEquals(200, responseCode);
    }

}
