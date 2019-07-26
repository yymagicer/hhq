package com.hhq.gateway.shiro;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class CustomizedSessionManager  extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        if (request instanceof HttpServletRequest) {
            System.out.println("getSessionId");
            String token = ((HttpServletRequest) request).getHeader("htk");
            if (token != null) {
                System.out.println("token="+token);
                return token;
            }
        }
        return null;
    }

}
