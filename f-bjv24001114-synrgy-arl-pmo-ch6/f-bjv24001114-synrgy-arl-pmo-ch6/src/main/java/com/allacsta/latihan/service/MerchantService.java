package com.allacsta.latihan.service;

import com.allacsta.latihan.entity.Merchant;
import com.allacsta.latihan.entity.Users;

import java.util.Map;
import java.util.UUID;

public interface MerchantService {

    Map save(Merchant request);
    Map edit(Merchant request);
    Map delete(Merchant request);
    Map list();

    Map deleteById(UUID id);

    Map getById(UUID id);

    Map getByMerchantName(String merchant_name);

    Map pagination(int page, int size);
}
