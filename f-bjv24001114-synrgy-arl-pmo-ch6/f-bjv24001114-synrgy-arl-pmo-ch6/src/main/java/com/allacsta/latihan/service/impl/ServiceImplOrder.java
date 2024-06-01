package com.allacsta.latihan.service.impl;

import com.allacsta.latihan.entity.Order;
import com.allacsta.latihan.entity.Product;
import com.allacsta.latihan.entity.Users;
import com.allacsta.latihan.repository.OrderRepository;
import com.allacsta.latihan.repository.UsersRepository;
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
    private UsersRepository usersRepository;

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

        if (request.getUsers() == null || request.getUsers().getId() == null) {
            return response.error("User is required.", 402);
        }

        Optional<Users> userOptional = usersRepository.findById(request.getUsers().getId());
        if (!userOptional.isPresent()) {
            return response.error("User not found.", 404);
        }

        try {
            // Set the user reference
            request.setUsers(userOptional.get());
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

        if (request.getUsers() != null && request.getUsers().getId() != null) {
            Optional<Users> userOptional = usersRepository.findById(request.getUsers().getId());
            if (!userOptional.isPresent()) {
                return response.error("User not found.", 404);
            }
            existingOrder.setUsers(userOptional.get());
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
        return null;
    }

    @Override
    public Map list() {
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
        return null;
    }

    @Override
    public Map pagination(int page, int size) {
        try {
            Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());
            Page<Order> list = orderRepository.getAllDataPage(show_data);
            return response.sukses(list);
        } catch (Exception e) {
            logger.error("Error fetching product pagination", e);
            return response.error("Error fetching product pagination.", 500);
        }
    }
}
