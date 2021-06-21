package com.cova.covaquizapp.util;

import lombok.NoArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@NoArgsConstructor
public class MethodUtils {

    public static String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }

    public static String prepareErrorJSON(HttpStatus status, Exception ex) {

        JSONObject errorJSON = new JSONObject();
        errorJSON.put("status", status.value());
        errorJSON.put("error", status.getReasonPhrase());
        errorJSON.put("message", Objects.requireNonNull(ex.getMessage()).split(":")[0]);
        return errorJSON.toString();
    }

}
