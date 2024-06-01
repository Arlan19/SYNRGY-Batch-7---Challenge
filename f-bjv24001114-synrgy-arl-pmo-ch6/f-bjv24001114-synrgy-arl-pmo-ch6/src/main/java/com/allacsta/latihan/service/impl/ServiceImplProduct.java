package com.allacsta.latihan.service.impl;

import com.allacsta.latihan.entity.Merchant;
import com.allacsta.latihan.entity.Product;
import com.allacsta.latihan.repository.MerchantRepository;
import com.allacsta.latihan.repository.ProductRepository;
import com.allacsta.latihan.service.ProductService;
import com.allacsta.latihan.utils.Response;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.*;

@Service
public class ServiceImplProduct implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ServiceImplProduct.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private Response response;
    @Override
    public Map save(Product request) {
        logger.info("Saving product: {}", request);
        if(response.chekNull(request.getProduct_name())){
            return  response.error("Name is required.",402);
        }
        if(StringUtils.isEmpty(request.getProduct_name())){
            return  response.error("Name is required.",402);
        }
        if(request.getMerchant() == null || request.getMerchant().getId() == null) {
            return response.error("Merchant is required.", 402);
        }
        Optional<Merchant> merchantOptional = merchantRepository.findById(request.getMerchant().getId());
        if(!merchantOptional.isPresent()){
            return response.error("Merchant not found", 404);
        }
//        request.setMerchant(merchantOptional.get());
//        return response.sukses(productRepository.save(request));

        request.setMerchant(merchantOptional.get());
        logger.info("Using existing merchant: {}", merchantOptional.get());

        Product savedProduct = productRepository.save(request);
        logger.info("Product saved: {}", savedProduct);
        return response.sukses(savedProduct);
    }

    @Override
    public Map edit(Product request) {
        try {
            if(response.chekNull(request.getId())){
                return  response.error("Id is required.",402);
            }

            Optional<Product> getId = productRepository.findById(request.getId());
            if(!getId.isPresent()){
                return response.error("id not found", 404);
            }
            Product edit = getId.get();
            edit.setProduct_name(request.getProduct_name());
            edit.setPrice(request.getPrice());
            edit.setMerchant(request.getMerchant());


            return response.sukses(productRepository.save(edit));

        }catch (Exception e){
            return response.error("id not found", 404);
        }
    }

    @Override
    public Map delete(Product request) {
        return null;
    }

    @Override
    public Map list() {
        return null;
    }

    @Override
    public Map deleteById(UUID id) {
        return null;
    }

    @Override
    public Map getById(UUID id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()){
            return response.error("id not found", 404);
        }
        productRepository.deleteById(id);
        return response.sukses(productOptional.get());
    }

    @Override
    public Map pagination(int page, int size) {
        try {
            Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());
            Page<Product> list = productRepository.getAllDataPage(show_data);
            return response.sukses(list);
        } catch (Exception e) {
            logger.error("Error fetching product pagination", e);
            return response.error("Error fetching product pagination.", 500);
        }
//        Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());
//        Page<Product> list = productRepository.getAllDataPage(show_data);
//        return response.sukses(list);
    }



    @Override
    public Map listWithFilters(String productName, Double price, UUID merchantId, Pageable pageable) {
        try {
            Specification<Product> spec = (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (productName != null && !productName.isEmpty()) {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("product_name")), "%" + productName.toLowerCase() + "%"));
                }
                if (price != null) {
                    predicates.add(criteriaBuilder.equal(root.get("price"), price));
                }
                if (merchantId != null) {
                    Join<Product, Merchant> merchantJoin = root.join("merchant");
                    predicates.add(criteriaBuilder.equal(merchantJoin.get("id"), merchantId));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };

            Page<Product> productList = productRepository.findAll(spec, pageable);
            return response.sukses(productList);
        } catch (Exception e) {
            logger.error("Error fetching product list with filters", e);
            return response.error("An error occurred while fetching the product list with filters.", 500);
        }
    }
}
