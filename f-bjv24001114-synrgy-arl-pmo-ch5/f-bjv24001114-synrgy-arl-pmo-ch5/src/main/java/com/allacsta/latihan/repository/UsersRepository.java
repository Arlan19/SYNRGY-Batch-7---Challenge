package com.allacsta.latihan.repository;

import com.allacsta.latihan.entity.OrderDetail;
import com.allacsta.latihan.entity.Users;
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
public interface UsersRepository extends JpaRepository<Users, UUID>, JpaSpecificationExecutor<Users> {

    public List<Users> findByUsername(String username);

    @Query("From Users u")
    public Page<Users> getAllDataPage(Pageable pageable);

    @Query(value = "select uk from Users uk WHERE uk.username = :namausername")
    public Optional<Users> getByUsername(@Param("namausername") String username);


}