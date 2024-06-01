package com.allacsta.latihan.service.impl;

import com.allacsta.latihan.entity.Users;
import com.allacsta.latihan.repository.UsersRepository;
import com.allacsta.latihan.service.UsersService;
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
public class ServiceImplUsers implements UsersService {

    private static final Logger logger = LoggerFactory.getLogger(ServiceImplUsers.class);

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private Response response;
    @Override
    public Map save(Users request) {
        if(response.chekNull(request.getUsername())){
            return  response.error("Name is required.",402);
        }
        if(StringUtils.isEmpty(request.getUsername())){
            return  response.error("Name is required.",402);
        }

        return response.sukses(usersRepository.save(request));
    }

    @Override
    public Map edit(Users request) {
        try {

            if(response.chekNull(request.getId())){
                return  response.error("Id is required.",402);
            }

            Optional<Users> getId = usersRepository.findById(request.getId());
            if(!getId.isPresent()){
                return response.error("id not found", 404);
            }

            Users edit = getId.get();
            edit.setUsername(request.getUsername());
            edit.setPassword(request.getPassword());
            edit.setEmail_address(request.getEmail_address());



            return response.sukses(usersRepository.save(edit));
        }catch (Exception e){
            return response.error("id not found", 404);

        }
    }

    @Override
    public Map delete(Users request) {
        return null;
    }

    @Override
    public Map list() {
        return null;
    }

    @Override
    public Map deleteById(UUID id) {
        Optional<Users> usersOptional = usersRepository.findById(id);
        if (!usersOptional.isPresent()){
            return  response.error("id not found", 404);
        }
        usersRepository.deleteById(id);
        return response.sukses(usersOptional.get());
    }

    @Override
    public Map getById(UUID id) {
        Map map = new HashMap();
        Optional<Users> getId = usersRepository.findById(id);
        if(!getId.isPresent()){
            return response.error("id not found", 404);
        }
        return response.sukses(getId.get());
    }

    @Override
    public Map getByUsername(String username) {
        Map map = new HashMap();
        Optional<Users> getByUsername = usersRepository.getByUsername(username);
        if(!getByUsername.isPresent()){
            return response.error("id not found", 404);
        }
        return response.sukses(getByUsername.get());
    }

    @Override
    public Map pagination(int page, int size) {
        Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Users> list = usersRepository.getAllDataPage(show_data);
        return response.sukses(list);
    }
}
