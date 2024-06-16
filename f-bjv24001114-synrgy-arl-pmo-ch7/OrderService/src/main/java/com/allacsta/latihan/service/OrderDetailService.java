package com.allacsta.latihan.service;


import com.allacsta.latihan.entity.OrderDetail;

import java.util.Map;
import java.util.UUID;

public interface OrderDetailService {

    Map save(OrderDetail request);
    Map edit(UUID id, OrderDetail request);
    Map delete(OrderDetail request);
    Map list();

    Map deleteById(UUID id);

    Map getById(UUID id);

    Map pagination(int page, int size);

}
