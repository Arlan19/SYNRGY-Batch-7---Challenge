package com.allacsta.latihan.controller;


import com.allacsta.latihan.entity.OrderDetail;
import com.allacsta.latihan.repository.OrderDetailRepository;
import com.allacsta.latihan.service.OrderDetailService;
import com.allacsta.latihan.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1/orderdetail")
public class OrderDetailController {

    @Autowired
    public OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    public Response response;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping(value = {"/list-orderdetail", "/list-orderdetail/"})
    public ResponseEntity<Map<String, Object>> getListOrderDetail() {
        try {
            logger.info("Fetching order details list...");
            Map<String, Object> result = orderDetailService.list();
            logger.info("Order details list fetched successfully");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching order details list", e);
            return new ResponseEntity<>(response.error("An error occurred while fetching the order details list.", 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = {"/save", "/save/"})
    public ResponseEntity<Map<String, Object>> saveOrderDetail(@RequestBody OrderDetail request) {
        try {
            logger.info("Request: {}", request);
            Map<String, Object> result = orderDetailService.save(request);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error saving order detail: ", e);
            return new ResponseEntity<>(response.error("An error occurred while saving the order detail.", 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = {"/update/{id}", "/update/{id}/"})
    public ResponseEntity<Map<String, Object>> editOrderDetail(@PathVariable UUID id, @RequestBody OrderDetail request) {
        try {
            logger.info("Updating order detail with id: {}", id);
            Map<String, Object> result = orderDetailService.edit(id, request);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating order detail: ", e);
            return new ResponseEntity<>(response.error("An error occurred while updating the order detail.", 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = {"/delete/{id}", "/delete/{id}/"})
    public ResponseEntity<Map<String, Object>> deleteOrderDetailById(@PathVariable UUID id) {
        try {
            logger.info("Deleting order detail with id: {}", id);
            Map<String, Object> result = orderDetailService.deleteById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting order detail: ", e);
            return new ResponseEntity<>(response.error("An error occurred while deleting the order detail.", 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = {"/get/{id}", "/get/{id}/"})
    public ResponseEntity<Map<String, Object>> getOrderDetailById(@PathVariable UUID id) {
        try {
            logger.info("Fetching order detail with id: {}", id);
            Map<String, Object> result = orderDetailService.getById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching order detail: ", e);
            return new ResponseEntity<>(response.error("An error occurred while fetching the order detail.", 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
