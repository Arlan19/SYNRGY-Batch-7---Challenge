package com.allacsta.latihan.service;

import com.allacsta.latihan.entity.Merchant;
import com.allacsta.latihan.entity.Users;

import java.util.Map;

public interface MerchantService {

    Map save(Merchant request);
    Map edit(Merchant request);
    Map delete(Merchant request);
    Map list();

    Map getById(Long id);

    Map getByMerchantName(String merchant_name);

    Map pagination(int page, int size);
}
