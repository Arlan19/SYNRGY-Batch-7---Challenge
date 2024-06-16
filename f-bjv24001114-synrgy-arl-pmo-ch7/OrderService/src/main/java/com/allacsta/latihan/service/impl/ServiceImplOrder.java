package com.allacsta.latihan.service.impl;

import com.allacsta.latihan.entity.Order;
import com.allacsta.latihan.repository.OrderRepository;
import com.allacsta.latihan.service.OrderService;
import com.allacsta.latihan.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.*;

@Service
public class ServiceImplOrder implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(ServiceImplOrder.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Response response;

    @Override
    public Map save(Order request) {
        logger.info("Saving order: {}", request);

        if (request == null) {
            return response.error("Order request is required.", 400);
        }

        if (StringUtils.isEmpty(request.getDestination_address())) {
            return response.error("Destination address is required.", 402);
        }

        if (request.getUserId() == null) {
            return response.error("User ID is required.", 402);
        }

        try {
            // Set the order time to current time
            request.setOrder_time(new Date());
            // Save the order
            Order savedOrder = orderRepository.save(request);
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("message", "Order saved successfully.");
            successResponse.put("order", savedOrder);
            return response.success(successResponse, 200);
        } catch (Exception e) {
            logger.error("Error saving order: ", e);
            return response.error("Internal server error.", 500);
        }
    }

    @Override
    public Map edit(UUID id, Order request) {
        logger.info("Editing order with id: {}", id);

        if (request == null) {
            return response.error("Order request is required.", 400);
        }

        Optional<Order> existingOrderOptional = orderRepository.findById(id);
        if (!existingOrderOptional.isPresent()) {
            return response.error("Order not found.", 404);
        }

        Order existingOrder = existingOrderOptional.get();

        if (!StringUtils.isEmpty(request.getDestination_address())) {
            existingOrder.setDestination_address(request.getDestination_address());
        }

        if (request.getCompleted() != null) {
            existingOrder.setCompleted(request.getCompleted());
        }

        if (request.getOrder_time() != null) {
            existingOrder.setOrder_time(request.getOrder_time());
        }

        if (request.getUserId() != null) {
            existingOrder.setUserId(request.getUserId());
        }

        try {
            // Save the updated order
            Order updatedOrder = orderRepository.save(existingOrder);
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("message", "Order updated successfully.");
            successResponse.put("order", updatedOrder);
            return response.success(successResponse, 200);
        } catch (Exception e) {
            logger.error("Error updating order: ", e);
            return response.error("Internal server error.", 500);
        }
    }

    @Override
    public Map delete(Order request) {
        // Implementasikan sesuai kebutuhan Anda
        return null;
    }

    @Override
    public Map list() {
        // Implementasikan sesuai kebutuhan Anda
        return null;
    }

    @Override
    public Map deleteById(UUID id) {
        logger.info("Deleting order with id: {}", id);

        Optional<Order> existingOrderOptional = orderRepository.findById(id);
        if (!existingOrderOptional.isPresent()) {
            return response.error("Order not found.", 404);
        }

        try {
            orderRepository.deleteById(id);
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("message", "Order deleted successfully.");
            return response.success(successResponse, 200);
        } catch (Exception e) {
            logger.error("Error deleting order: ", e);
            return response.error("Internal server error.", 500);
        }
    }

    @Override
    public Map getById(UUID id) {
        // Implementasikan sesuai kebutuhan Anda
        return null;
    }

    @Override
    public Map pagination(int page, int size) {
//        try {
//            Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());
//            Page<Order> list = orderRepository.findAll(show_data);
//            return response.success(list);
//        } catch (Exception e) {
//            logger.error("Error fetching order pagination", e);
//            return response.error("Error fetching order pagination.", 500);
//        }
        return null;
    }
}