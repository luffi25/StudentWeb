package uz.isystem.studentweb.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.isystem.studentweb.security.CustomUserDetail;

public class SpringSecurityUtil {
    public static Integer getUserId(){
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
        return userDetail.getId();
    }
}
