package com.btca.bootcampalarm.util;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class MailUtils {

    public boolean mailFormatCheck(String mail) {
        return Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
                .matcher(mail)
                .matches();
    }
}
