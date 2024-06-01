package com.allacsta.latihan.service;

import com.allacsta.latihan.entity.OrderDetail;
import com.allacsta.latihan.entity.Users;

import java.util.Map;
import java.util.UUID;

public interface UsersService {
    Map save(Users request);
    Map edit(Users request);
    Map delete(Users request);
    Map list();

    Map deleteById(UUID id);

    Map getById(UUID id);

    Map getByUsername(String username);

    Map pagination(int page, int size);
}
