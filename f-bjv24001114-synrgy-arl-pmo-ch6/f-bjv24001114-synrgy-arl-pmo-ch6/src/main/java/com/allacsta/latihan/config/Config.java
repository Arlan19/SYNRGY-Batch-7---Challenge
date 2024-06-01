package com.allacsta.latihan.config;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@Component
@Data
public class Config {

    public static String APP_NAME = "name";

    public static String yourName() {
        return "Arlan";
    }

    String code = "status", message = "message";

    public String code_notFound ="404";

    public String codeRequired ="403";
    public String isRequired =" is Required";

    public String code_sukses = "200";
    public String code_server = "500";
    public String code_null = "1";
    public String message_sukses = "sukses";

    public String convertDateToString(Date date) {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }
}
