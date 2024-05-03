//package com.allacsta.latihan.controller;
//
//import com.allacsta.latihan.config.Config;
//import com.allacsta.latihan.entity.OrderDetail;
//import com.allacsta.latihan.repository.OrderDetailRepository;
//import com.allacsta.latihan.service.OrderDetailService;
//import com.allacsta.latihan.sp.QuerySPOrderDetail;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.web.bind.annotation.*;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/v1/orderdetail")
//public class OrderDetailController {
//
//    //CURD : method CURD ada dimana?
//    @Autowired
//    private OrderDetailService orderDetailService;
//
//    Config config = new Config(); // tampa DI -> Depedency injection
//
//    //JIKa pake DI
//    @Autowired
//    private Config configWithDI;
//
////    public  EmployeeController (ServiceEmployee serviceEmployee){
////
////    }
//
//    private static final Logger logger = LoggerFactory.getLogger(OrderDetailController.class);
//
//    @GetMapping(value = "")
//    public String hello(){
//        return "Hello Word";
//
//    }
//
//
//    @PostMapping(value = "/save")
//    public Map hello(@RequestBody OrderDetail request){
//        Map map = new HashMap();
//        try {
//            logger.info("Request:",request);
//            map =  orderDetailService.save(request);
//            return map;
//        }catch (Exception e){
//            logger.info("Eror hello :",e.getMessage());
//            return map;
//        }
//
//    }
//
//
//
//    @Autowired
//    private DataSource dataSource;
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//    @Autowired
//    public OrderDetailRepository orderDetailRepository;
//    @Autowired
//    public QuerySPOrderDetail querySPOrderDetail;
//
//    @GetMapping(value = {"/list", "/list/"})
//    public ResponseEntity<Map> getById() {
//        Map map = new HashMap();
//        map.put("list",orderDetailRepository.getListSP());
//        return new ResponseEntity<Map>(map, HttpStatus.OK);
//    }
//    @GetMapping(value = {"/inject", "/inject/"})
//    public ResponseEntity<String> inject() {
//
//        jdbcTemplate.execute(querySPOrderDetail.getData);
//        jdbcTemplate.execute(querySPOrderDetail.getDataEmployeeLikeName);
//        jdbcTemplate.execute(querySPOrderDetail.insertEmployee);
//        jdbcTemplate.execute(querySPOrderDetail.updateEmployee);
//        jdbcTemplate.execute(querySPOrderDetail.deletedEmployee);
//        return new ResponseEntity<String>("Success", HttpStatus.OK);
//    }
//
//}
