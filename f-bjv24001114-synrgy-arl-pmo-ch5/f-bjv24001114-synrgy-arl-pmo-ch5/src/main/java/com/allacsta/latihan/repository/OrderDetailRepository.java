//package com.allacsta.latihan.repository;
//
//import com.allacsta.latihan.entity.OrderDetail;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.query.Procedure;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Repository
//public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>, JpaSpecificationExecutor<OrderDetail> {
//
////    public List<OrderDetail> findByID(Long id);
////
////    public List<OrderDetail> fingByIDAndQuantity(Long id, Long quatity);
//
//
//    @Query("From OrderDetail u")
//    public Page<OrderDetail> getAllDataPage(Pageable pageable);
//
////    @Query(value = "select uk from OrderDetail uk WHERE uk.id = :id")
////    public Optional<OrderDetail> getById(@Param("id") UUID id);
//
//    @Query(value = "select uk from OrderDetail uk WHERE uk.quantity = :namaquantity")
//    public Optional<OrderDetail> getByQuantity(@Param("namaquantity") Long quantity);
//
////    @Query("FROM OrderDetail u WHERE LOWER(u.name) like LOWER(:nameParam)")
////    public List<OrderDetail> findByNameLike(String nameParam);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    //JPA Query
//    @Query(value = "select c from OrderDetail c WHERE c.id = :idOrderDetail", nativeQuery = false)
//    public OrderDetail getById(@Param("idOrderDetail") Long idOrderDetail);
//
//    //Native Query : menggunakan JPAQL
//    @Query(value = "select  e from OrderDetail e where  id = :idOrderDetail;",nativeQuery = true)
//    public Object getByIdNativeQuery(@Param("idOrderDetail") Long idEmployee);
//
//    //Store prosedure
//    @Query(value = "select * from getorderdetaillist()",nativeQuery = true)
//    public List<Object> getListSP();
//
//    @Procedure("insert1")
//    void saveOrderDetailSP(@Param("resid") Long resid, @Param("rqnama") String rqnama);
//
//    @Procedure("update_employee")
//    void updateOrderDetailSP(@Param("resid") Long resid,@Param("rqnama") String rqnama);
//
//    @Procedure("deleted_employee1")
//    void deleted_order_detail1(@Param("resid") Long resid);
//
//    @Procedure("getemployeebyid")
//    Object getorderdetailbyid(@Param("rqid") Long rqid);
//}
