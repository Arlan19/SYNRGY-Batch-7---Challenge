//package com.allacsta.latihan.sp;
//
//
//
//import com.allacsta.latihan.repository.OrderDetailRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.sql.DataSource;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class TestingSp {
//    @Autowired
//    private DataSource dataSource;
//    @Autowired
//    public OrderDetailRepository orderDetailRepository;
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public QuerySPOrderDetail querySPOrderDetail;
//    @Before
//    public void init() {
//        try {
//            jdbcTemplate.execute(querySPOrderDetail.getData);
//            jdbcTemplate.execute(querySPOrderDetail.getDataEmployeeLikeName);
//            jdbcTemplate.execute(querySPOrderDetail.insertEmployee);
//            jdbcTemplate.execute(querySPOrderDetail.updateEmployee);
//            jdbcTemplate.execute(querySPOrderDetail.deletedEmployee);
//        } finally {
////            session.close();
//        }
//    }
//
//    @Test
//    public void listSP(){
//        List<Object> obj =  orderDetailRepository.getListSP();
//        System.out.println(obj);
//    }
//    @Test
//    public void getIDSP(){
//        Object obj =  orderDetailRepository.getorderdetailbyid(6L);
//        System.out.println(obj);
//    }
//
////    @Test
////    public void getIdSp(){
////        Object obj =  employeeRepository.getemployeebyid(6L);
////        System.out.println(obj);
////    }
////
////    @Test
////    public void saveSp(){
////        employeeRepository.saveEmployeeSP(null, "spring boot1");
////    }
////
////    @Test
////    public void udpateSP(){
////        employeeRepository.updateEmployeeSP(6L, "spring boot1");
////    }
////    @Test
////    public void deletedSp(){
////        employeeRepository.deleted_employee1(8L);
////    }
//
//}
