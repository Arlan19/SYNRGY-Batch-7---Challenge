package com.allacsta.latihan.controller;


import com.allacsta.latihan.entity.Users;
import com.allacsta.latihan.repository.UsersRepository;
import com.allacsta.latihan.service.UsersService;
import com.allacsta.latihan.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("v1/users")
public class UsersController {

    @Autowired
    public UsersRepository usersRepository;

    @Autowired
    public UsersService usersService;

    @Autowired
    public Response response;

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @GetMapping(value = {"/list-users","/list-users/"})
    public ResponseEntity<Map> getListUsers(){
        return new ResponseEntity<Map>(response.sukses(usersService.pagination(0, 10)), HttpStatus.OK);
    }

    @PostMapping(value = {"/save", "/save/"})
    public Map saveUser(@RequestBody Users request){
        Map map = new HashMap();
        try {
            logger.info("Request",request);
            map = usersService.save(request);
            return map;
        }catch (Exception e){
            logger.info("Error : ", e.getMessage());
            return map;
        }
    }

//    @PutMapping(value = {"/update","/update/"})
//    public ResponseEntity<Map> updateUsers(@RequestBody Users request){
//        return new ResponseEntity<Map>(response.sukses(request), HttpStatus.OK);
//    }

    @PutMapping(value = {"/update/{id}","/update/{id}/"})
    public Map updateUser(@PathVariable UUID id, @RequestBody Users request){
        Map map = new HashMap();
        try {
            request.setId(id);
            logger.info("Request",request);
            map = usersService.edit(request);
            return map;
        }catch (Exception e){
            logger.info("Error : ", e.getMessage());
            return map;
        }

    }

    @DeleteMapping(value = {"/deleted", "/deleted/"})
    public ResponseEntity<Map> deleteUsers(@RequestBody Users request) {
        return new ResponseEntity<Map>(response.sukses(request), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/deleted/{id}", "/deleted/{id}"})
    public Map deleteUsersById(@PathVariable UUID id) {
        Map map = new HashMap();
        try {
            map = usersService.deleteById(id);
            return map;
        }catch (Exception e){
            logger.info("Error : ", e.getMessage());
            return response.error("An error occurred while deleting the merchant.", 500);
        }
    }
}
