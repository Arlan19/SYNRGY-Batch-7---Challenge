package com.allacsta.latihan.testing;

import com.allacsta.latihan.entity.Merchant;
import com.allacsta.latihan.service.MerchantService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestingMerchant {

    @Autowired
    public MerchantService merchantService;

    @Autowired
    public TestRestTemplate restTemplate;

    @Test
    public void saveMerchant(){
        Merchant save = new Merchant();
        save.setMerchant_name("KFC");
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

    @Test
    public void listSukses() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");


        ResponseEntity<Object> exchange = restTemplate.exchange("http://dev.farizdotid.com/api/daerahindonesia/provinsi", HttpMethod.GET, null, Object.class);
        System.out.println("response  =" + exchange.getBody());

        // get value
//        assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }

    @Test
    public void listSuksesMerchant() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");


        ResponseEntity<Object> exchange = restTemplate.exchange("http://localhost:8081/v1/merchant/list-merchant", HttpMethod.GET, null, Object.class);
        System.out.println("response  =" + exchange.getBody());

        // get value
//        assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }

}
