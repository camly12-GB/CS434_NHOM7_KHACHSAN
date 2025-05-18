package com.example.thangcachep.demo.security;

import com.example.thangcachep.demo.constant.SystemConstant;
import com.example.thangcachep.demo.security.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication ) throws IOException {
        String targerUrl = determinedTargetUrl(authentication);
        if(response.isCommitted()){
            System.out.println("Can't redirect");
            return;
        }
        redirectStrategy.sendRedirect(request,response,targerUrl);
    }

    private boolean isAdmin(List<String> roles) {
        if (roles.contains(SystemConstant.ADMIN_ROLE) || roles.contains(SystemConstant.MANAGER_ROLE)) {
            return true;
        }
        return false;
    }

    private boolean isUser(List<String> roles) {
        if (roles.contains(SystemConstant.USER_ROLE)) {
            return true;
        }
        return false;
    }
//xac dinh vai tro de chuyen huong url
    public String determinedTargetUrl(Authentication authentication){
        String url = "";
        List<String> roles = SecurityUtils.getAuthorities();
        if(isUser(roles)){
            url = SystemConstant.HOME;
        }else if(isAdmin(roles)){
            url = SystemConstant.HOME;
        }
        return  url;
    }




}
