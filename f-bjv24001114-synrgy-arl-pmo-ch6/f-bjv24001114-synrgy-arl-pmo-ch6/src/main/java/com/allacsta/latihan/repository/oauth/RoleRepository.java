package com.allacsta.latihan.repository.oauth;


import com.allacsta.latihan.entity.oauth.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    Role findOneByName(String name);

    List<Role> findByNameIn(String[] names);
}

