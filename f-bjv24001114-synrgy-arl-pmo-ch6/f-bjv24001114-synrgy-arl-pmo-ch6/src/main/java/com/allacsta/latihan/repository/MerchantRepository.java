package com.allacsta.latihan.repository;

import com.allacsta.latihan.entity.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, UUID>, JpaSpecificationExecutor<Merchant> {

//    public List<Merchant> findByMerchantName(String merchant_name);

    @Query("From Merchant u")
    public Page<Merchant> getAllDataPage(Pageable pageable);

    @Query(value = "select uk from Merchant uk WHERE uk.merchant_name = :namamerchantname")
    public Optional<Merchant> getByMerchantName(@Param("namamerchantname") String merchant_name);


}
