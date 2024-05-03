//package com.allacsta.latihan.service.impl;
//
//import com.allacsta.latihan.entity.OrderDetail;
//import com.allacsta.latihan.repository.OrderDetailRepository;
//import com.allacsta.latihan.service.OrderDetailService;
//import com.allacsta.latihan.utils.Response;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.util.StringUtils;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//@Service
//public class ServiceImplOrderDetail implements OrderDetailService {
//
//    private static final Logger logger = LoggerFactory.getLogger(ServiceImplOrderDetail.class);
//
//    @Autowired
//    private OrderDetailRepository orderDetailRepository;
//    @Autowired
//    private Response response;
//
//    @Override
//    public Map save(OrderDetail request) {
//        if(response.chekNull(request.getId())){
//            return response.error("id is required", 402);
//        }
//        Optional<OrderDetail> getId = orderDetailRepository.findById(request.getId());
//        if(!getId.isPresent()){
//            return response.error("id not found", 404);
//        }
//        return response.sukses(orderDetailRepository.save(request));
//    }
//
//    @Override
//    public Map edit(OrderDetail request) {
//        try {
//            if (response.chekNull(request.getId())) {
//                return response.error("Id is Required", 402);
//            }
//
//            Optional<OrderDetail> getId = orderDetailRepository.findById(request.getId());
//            if (!getId.isPresent()) {
//                return response.error("id not found", 404);
//            }
//
//            OrderDetail edit = getId.get();
//            edit.setQuantity(request.getQuantity());
//
//            return response.sukses(orderDetailRepository.save(request));
//        } catch (Exception e) {
//            return response.error("id not found", 404);
//        }
//    }
//
//    @Override
//    public Map delete(OrderDetail request) {
//        return null;
//    }
//
//    @Override
//    public Map list() {
//        return null;
//    }
//
//    @Override
//    public Map getById(Long id) {
//        Map map = new HashMap();
//        Optional<OrderDetail> getId = orderDetailRepository.findById(id);
//        if(!getId.isPresent()){
//            return response.error("id not found", 404);
//        }
//        return response.sukses(getId.get());
//    }
//
//    @Override
//    public Map getByQuantity(Long quantity) {
//        Map map = new HashMap();
//        Optional<OrderDetail> getByName = orderDetailRepository.getByQuantity(quantity);
//        if(!getByName.isPresent()){
//            return response.error("id not found", 404);
//        }
//        return response.sukses(getByName.get());
//    }
//
//    @Override
//    public Map pagination(int page, int size) {
//        Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());
//        Page<OrderDetail> list = orderDetailRepository.getAllDataPage(show_data);
//        return response.sukses(list);
//    }
//}
