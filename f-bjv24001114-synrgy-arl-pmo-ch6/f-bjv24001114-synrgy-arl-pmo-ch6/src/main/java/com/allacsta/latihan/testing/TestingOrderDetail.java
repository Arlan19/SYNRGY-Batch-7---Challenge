package com.allacsta.latihan.testing;

import com.allacsta.latihan.entity.OrderDetail;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class TestingOrderDetail {
//
//    @Autowired
//    public OrderDetailService orderDetailService;
//
//    @Test
//    public void saveOrderDetail(){
//        OrderDetail save = new OrderDetail();
//        save.setQuantity(10L);
//
//        Map map =  orderDetailService.save(save);
//        int responseCode = (Integer) map.get("status");
//        Assert.assertEquals(200, responseCode);
//    }
//}
