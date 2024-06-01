package com.allacsta.latihan.service.impl;

import com.allacsta.latihan.entity.Order;
import com.allacsta.latihan.entity.OrderDetail;
import com.allacsta.latihan.entity.Product;
import com.allacsta.latihan.repository.OrderDetailRepository;
import com.allacsta.latihan.repository.OrderRepository;
import com.allacsta.latihan.repository.ProductRepository;
import com.allacsta.latihan.service.OrderDetailService;
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
public class ServiceImplOrderDetail implements OrderDetailService {

    private static final Logger logger = LoggerFactory.getLogger(ServiceImplOrderDetail.class);

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Response response;


    @Override
    public Map save(OrderDetail request) {
        logger.info("Saving order detail: {}", request);

        if (request == null) {
            return response.error("OrderDetail request is required.", 400);
        }

        if (request.getOrder() == null || request.getOrder().getId() == null) {
            return response.error("Order is required.", 402);
        }

        if (request.getProduct() == null || request.getProduct().getId() == null) {
            return response.error("Product is required.", 402);
        }

        Optional<Order> orderOptional = orderRepository.findById(request.getOrder().getId());
        if (!orderOptional.isPresent()) {
            return response.error("Order not found.", 404);
        }

        Optional<Product> productOptional = productRepository.findById(request.getProduct().getId());
        if (!productOptional.isPresent()) {
            return response.error("Product not found.", 404);
        }

        try {
            // Calculate total price
            double price = productOptional.get().getPrice();
            double totalPrice = price * request.getQuantity();
            request.setTotal_price(totalPrice);

            request.setOrder(orderOptional.get());
            request.setProduct(productOptional.get());
            OrderDetail savedOrderDetail = orderDetailRepository.save(request);
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("message", "OrderDetail saved successfully.");
            successResponse.put("orderDetail", savedOrderDetail);
            return response.success(successResponse, 200);
        } catch (Exception e) {
            logger.error("Error saving order detail: ", e);
            return response.error("Internal server error.", 500);
        }
    }

    @Override
    public Map edit(UUID id, OrderDetail request) {
        logger.info("Editing order detail with id: {}", id);

        if (request == null) {
            return response.error("OrderDetail request is required.", 400);
        }

        Optional<OrderDetail> existingOrderDetailOptional = orderDetailRepository.findById(id);
        if (!existingOrderDetailOptional.isPresent()) {
            return response.error("OrderDetail not found.", 404);
        }

        OrderDetail existingOrderDetail = existingOrderDetailOptional.get();

        if (request.getQuantity() > 0) {
            existingOrderDetail.setQuantity(request.getQuantity());
        }

        if (request.getTotal_price() > 0) {
            existingOrderDetail.setTotal_price(request.getTotal_price());
        }

        if (request.getOrder() != null && request.getOrder().getId() != null) {
            Optional<Order> orderOptional = orderRepository.findById(request.getOrder().getId());
            if (!orderOptional.isPresent()) {
                return response.error("Order not found.", 404);
            }
            existingOrderDetail.setOrder(orderOptional.get());
        }

        if (request.getProduct() != null && request.getProduct().getId() != null) {
            Optional<Product> productOptional = productRepository.findById(request.getProduct().getId());
            if (!productOptional.isPresent()) {
                return response.error("Product not found.", 404);
            }
            existingOrderDetail.setProduct(productOptional.get());
        }

        try {
            OrderDetail updatedOrderDetail = orderDetailRepository.save(existingOrderDetail);
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("message", "OrderDetail updated successfully.");
            successResponse.put("orderDetail", updatedOrderDetail);
            return response.success(successResponse, 200);
        } catch (Exception e) {
            logger.error("Error updating order detail: ", e);
            return response.error("Internal server error.", 500);
        }
    }

    @Override
    public Map delete(OrderDetail request) {
        if (request == null || request.getId() == null) {
            return response.error("OrderDetail ID is required.", 400);
        }

        Optional<OrderDetail> existingOrderDetailOptional = orderDetailRepository.findById(request.getId());
        if (!existingOrderDetailOptional.isPresent()) {
            return response.error("OrderDetail not found.", 404);
        }

        try {
            orderDetailRepository.delete(request);
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("message", "OrderDetail deleted successfully.");
            return response.success(successResponse, 200);
        } catch (Exception e) {
            logger.error("Error deleting order detail: ", e);
            return response.error("Internal server error.", 500);
        }
    }

    @Override
    public Map list() {
        try {
            List<OrderDetail> orderDetails = orderDetailRepository.findAll();
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("orderDetails", orderDetails);
            return response.success(successResponse, 200);
        } catch (Exception e) {
            logger.error("Error fetching order details list: ", e);
            return response.error("Internal server error.", 500);
        }
    }

    @Override
    public Map deleteById(UUID id) {
        logger.info("Deleting order detail with id: {}", id);

        Optional<OrderDetail> existingOrderDetailOptional = orderDetailRepository.findById(id);
        if (!existingOrderDetailOptional.isPresent()) {
            return response.error("OrderDetail not found.", 404);
        }

        try {
            orderDetailRepository.deleteById(id);
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("message", "OrderDetail deleted successfully.");
            return response.success(successResponse, 200);
        } catch (Exception e) {
            logger.error("Error deleting order detail: ", e);
            return response.error("Internal server error.", 500);
        }
    }

    @Override
    public Map getById(UUID id) {
        logger.info("Fetching order detail with id: {}", id);

        Optional<OrderDetail> existingOrderDetailOptional = orderDetailRepository.findById(id);
        if (!existingOrderDetailOptional.isPresent()) {
            return response.error("OrderDetail not found.", 404);
        }

        try {
            OrderDetail orderDetail = existingOrderDetailOptional.get();
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("orderDetail", orderDetail);
            return response.success(successResponse, 200);
        } catch (Exception e) {
            logger.error("Error fetching order detail: ", e);
            return response.error("Internal server error.", 500);
        }
    }

    @Override
    public Map pagination(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            Page<OrderDetail> orderDetailPage = orderDetailRepository.getAllDataPage(pageable);
            return response.sukses(orderDetailPage);
        } catch (Exception e) {
            logger.error("Error fetching order detail pagination", e);
            return response.error("Error fetching order detail pagination.", 500);
        }
    }
}
