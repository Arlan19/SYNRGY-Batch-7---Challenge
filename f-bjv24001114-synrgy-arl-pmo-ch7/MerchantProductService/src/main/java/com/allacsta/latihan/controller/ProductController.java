package com.allacsta.latihan.controller;


import com.allacsta.latihan.entity.Product;
import com.allacsta.latihan.repository.ProductRepository;
import com.allacsta.latihan.service.ProductService;
import com.allacsta.latihan.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("v1/product")
public class ProductController {

    @Autowired
    public ProductRepository productRepository;
    @Autowired
    public ProductService productService;

    @Autowired
    public Response response;

    private static final Logger logger = LoggerFactory.getLogger(MerchantController.class);

    @GetMapping(value = {"/list-product","/list-product/"})
    public ResponseEntity<Map> getListProduct(){
        try {
            logger.info("Fetching product list...");
            Map result = response.sukses(productService.pagination(0, 10));
            logger.info("Product list fetched successfully");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching product list", e);
            return new ResponseEntity<>(response.error("An error occurred while fetching the product list.", 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        return new ResponseEntity<Map>(response.sukses(productService.pagination(0, 10)), HttpStatus.OK);
    }

    @PostMapping(value = {"/save", "/save/"})
    public Map saveProduct(@RequestBody Product request){
        Map map = new HashMap();
        try {
            logger.info("Request",request);
            map = productService.save(request);
            return map;
        }catch (Exception e){
            logger.info("Error : ", e.getMessage());
            return map;
        }
    }

    @PutMapping(value = {"/update/{id}","/update/{id}/"})
    public Map updateProduct(@PathVariable UUID id, @RequestBody Product request){
        Map map = new HashMap();
        try {
            request.setId(id);
            logger.info("Request",request);
            map = productService.edit(request);
            return map;
        }catch (Exception e){
            logger.info("Error : ", e.getMessage());
            return map;
        }
    }

    @DeleteMapping(value = {"/deleted/{id}", "/deleted/{id}"})
    public Map deleteProductById(@PathVariable UUID id) {
        Map map = new HashMap();
        try {
            map = productService.deleteById(id);
            return map;
        } catch (Exception e){
            logger.info("Error : ", e.getMessage());
            return response.error("An error occurred while deleting the product.", 500);
        }
    }

    @GetMapping(value = {"/getbyid/{id}", "/getbyid/{id}/"})
    public Map getById(@PathVariable UUID id){
        Map map = new HashMap();
        try{
            map = productService.getById(id);
            return map;
        } catch (Exception e){
            logger.info("Error : ", e.getMessage());
            return response.error("An error occurred while get the merchant.", 500);
        }
    }

//    @GetMapping(value = { "/list-product-pagination", "/list-product-pagination/" })
//    public ResponseEntity<Map> list(@RequestParam(required = false, name = "product_name") String product_name,
//                                    @RequestParam(required = false, name = "price") Double price,
//                                    @RequestParam(required = false, name = "merchant") Merchant merchant,
//                                    @PageableDefault(page = 0, size = 10) Pageable pageable) {
//
//
//        Specification<Product> spec = ((root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            if (product_name != null && !product_name.isEmpty()) {
//                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("product_name")), "%" + product_name.toLowerCase() + "%"));
//            }
//            if (price != null ) {
//                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("price")), "%" + price + "%"));
//            }
//            if (merchant != null ) {
//                predicates.add(criteriaBuilder.equal(root.get("merchant"), merchant));
//            }
//
////            Aaa = Aaa
//            // AaaA=aAaA
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        });
//
//        Page<Product> clientCompanyList = productRepository.findAll(spec, pageable);
//        return new ResponseEntity<Map>(response.sukses(clientCompanyList), HttpStatus.OK);
//    }

//    @GetMapping(value = {"/list-product-pagination", "/list-product-pagination/"})
//    public ResponseEntity<Map> list(@RequestParam(required = false, name = "product_name") String product_name,
//                                    @RequestParam(required = false, name = "price") Double price,
//                                    @RequestParam(required = false, name = "merchant_id") UUID merchantId,
//                                    @PageableDefault(page = 0, size = 10) Pageable pageable) {
//
//        Specification<Product> spec = ((root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            if (product_name != null && !product_name.isEmpty()) {
//                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("product_name")), "%" + product_name.toLowerCase() + "%"));
//            }
//            if (price != null) {
//                predicates.add(criteriaBuilder.equal(root.get("price"), price));
//            }
//            if (merchantId != null) {
//                Join<Product, Merchant> merchantJoin = root.join("merchant");
//                predicates.add(criteriaBuilder.equal(merchantJoin.get("id"), merchantId));
//            }
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        });
//
//        Page<Product> productList = productRepository.findAll(spec, pageable);
//        return new ResponseEntity<>(response.sukses(productList), HttpStatus.OK);
//    }

    @GetMapping(value = { "/list-product-pagination", "/list-product-pagination/" })
    public ResponseEntity<Map> list(
            @RequestParam(required = false, name = "product_name") String product_name,
            @RequestParam(required = false, name = "price") Double price,
            @RequestParam(required = false, name = "merchant_id") UUID merchantId,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        try {
            Map result = productService.listWithFilters(product_name, price, merchantId, pageable);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching product list", e);
            return new ResponseEntity<>(response.error("An error occurred while fetching the product list.", 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
