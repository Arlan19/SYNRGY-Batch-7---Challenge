package com.allacsta.latihan.controller;

import com.allacsta.latihan.entity.Order;
import com.allacsta.latihan.repository.OrderRepository;
import com.allacsta.latihan.service.OrderService;
import com.allacsta.latihan.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("v1/order")
public class OrderController {

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public OrderService orderService;

    @Autowired
    public Response response;

    private static final Logger logger = LoggerFactory.getLogger(MerchantController.class);

    @GetMapping(value = {"/list-order","/list-order/"})
    public ResponseEntity<Map> getListOrder(){
        try {
            logger.info("Fetching product list...");
            Map result = response.sukses(orderService.pagination(0, 10));
            logger.info("Product list fetched successfully");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching product list", e);
            return new ResponseEntity<>(response.error("An error occurred while fetching the product list.", 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = {"/save", "/save/"})
    public Map saveOrder(@RequestBody Order request){
        Map map = new HashMap();
        try {
            logger.info("Request",request);
            map = orderService.save(request);
            return map;
        }catch (Exception e){
            logger.info("Error : ", e.getMessage());
            return map;
        }
    }

    @PutMapping(value = {"/update/{id}","/update/{id}/"})
    public ResponseEntity<Map<String, Object>> editOrder(@PathVariable UUID id, @RequestBody Order orderRequest) {
        Map<String, Object> response = orderService.edit(id, orderRequest);
        return ResponseEntity.status((int) response.get("status")).body(response);
    }

    @DeleteMapping(value = {"/deleted/{id}", "/deleted/{id}"})
    public ResponseEntity<Map<String, Object>> deleteOrderById(@PathVariable UUID id) {
        Map<String, Object> response = orderService.deleteById(id);
        return ResponseEntity.status((int) response.get("status")).body(response);
    }

}
