package com.allacsta.latihan.service.impl;

import com.allacsta.latihan.entity.Merchant;
import com.allacsta.latihan.repository.MerchantRepository;
import com.allacsta.latihan.service.MerchantService;
import com.allacsta.latihan.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceImplMerchant implements MerchantService {

    private static final Logger logger = LoggerFactory.getLogger(ServiceImplUsers.class);

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private Response response;

    @Override
    public Map save(Merchant request) {
        if(response.chekNull(request.getMerchant_name())){
            return  response.error("Name is required.",402);
        }
        if(StringUtils.isEmpty(request.getMerchant_name())){
            return  response.error("Name is required.",402);
        }

        return response.sukses(merchantRepository.save(request));
    }

    @Override
    public Map edit(Merchant request) {
        try {

            if(response.chekNull(request.getId())){
                return  response.error("Id is required.",402);
            }

            Optional<Merchant> getId = merchantRepository.findById(request.getId());
            if(!getId.isPresent()){
                return response.error("id not found", 404);
            }

            Merchant edit = getId.get();
            edit.setMerchant_name(request.getMerchant_name());
            edit.setMerchant_location(request.getMerchant_location());
            edit.setOpen(request.getOpen());



            return response.sukses(merchantRepository.save(edit));
        }catch (Exception e){
            return response.error("id not found", 404);

        }
    }

    @Override
    public Map delete(Merchant request) {
        return null;
    }

    @Override
    public Map list() {
        return null;
    }

    @Override
    public Map deleteById(UUID id) {
        Optional<Merchant> merchantOptional = merchantRepository.findById(id);
        if (!merchantOptional.isPresent()){
            return response.error("id not found", 404);
        }
        merchantRepository.deleteById(id);
        return response.sukses(merchantOptional.get());
    }

    @Override
    public Map getById(UUID id) {
        Map map = new HashMap();
        Optional<Merchant> getId = merchantRepository.findById(id);
        if(!getId.isPresent()){
            return response.error("id not found", 404);
        }
//        merchantRepository.getById(id);
        return response.sukses(getId.get());
    }

    @Override
    public Map getByMerchantName(String merchant_name) {
        Map map = new HashMap();
        Optional<Merchant> getByName = merchantRepository.getByMerchantName(merchant_name);
        if(!getByName.isPresent()){
            return response.error("id not found", 404);
        }
        return response.sukses(getByName.get());
    }

    @Override
    public Map pagination(int page, int size) {
        Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Merchant> list = merchantRepository.getAllDataPage(show_data);
        return response.sukses(list);
    }
}
