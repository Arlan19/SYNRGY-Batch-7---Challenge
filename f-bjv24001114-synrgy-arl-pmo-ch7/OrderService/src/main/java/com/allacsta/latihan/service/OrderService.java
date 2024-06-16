package com.allacsta.latihan.service;



import com.allacsta.latihan.entity.Order;

import java.util.Map;
import java.util.UUID;

public interface OrderService {

    Map save(Order request);
    Map edit(UUID id, Order request);
    Map delete(Order request);
    Map list();

    Map deleteById(UUID id);

    Map getById(UUID id);

    Map pagination(int page, int size);

}
