package com.allacsta.latihan.service.register;



import com.allacsta.latihan.dto.req.LoginModel;
import com.allacsta.latihan.dto.req.RegisterModel;

import java.security.Principal;
import java.util.Map;

public interface UserService {
    Map registerManual(RegisterModel objModel) ;

    Map registerByGoogle(RegisterModel objModel) ;

    public Map getDetailProfile(Principal principal);

    public Map login(LoginModel objLogin);
}

