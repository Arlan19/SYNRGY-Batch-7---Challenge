package com.allacsta.latihan.dto.req;

import lombok.Data;


@Data
public class ResetPasswordModel {
    public String email;

    public String otp;
    public String newPassword;
}

