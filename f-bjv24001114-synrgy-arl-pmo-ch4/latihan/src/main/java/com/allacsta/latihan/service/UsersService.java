package com.allacsta.latihan.service;

import com.allacsta.latihan.entity.OrderDetail;
import com.allacsta.latihan.entity.Users;

import java.util.Map;

public interface UsersService {
    Map save(Users request);
    Map edit(Users request);
    Map delete(Users request);
    Map list();

    Map getById(Long id);

    Map getByUsername(String username);

    Map pagination(int page, int size);
}
