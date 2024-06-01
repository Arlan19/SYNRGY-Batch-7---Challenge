package com.allacsta.latihan.testing;

import com.allacsta.latihan.entity.Users;
import com.allacsta.latihan.service.UsersService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestingUsers {
    @Autowired
    public UsersService usersService;

    @Test
    public void saveUsers(){
        Users save = new Users();
        save.setUsername("Joko");
        save.setPassword("adsfsad");
        save.setEmail_address("Joko@Gmail.com");

        Map map =  usersService.save(save);
        int responseCode = (Integer) map.get("status");
        Assert.assertEquals(200, responseCode);
    }


    @Test
    public void getPagination(){
        Map map =  usersService.pagination(0,10);
        System.out.println(map);
        int responseCode = (Integer) map.get("status");
        Assert.assertEquals(200, responseCode);
    }

}
