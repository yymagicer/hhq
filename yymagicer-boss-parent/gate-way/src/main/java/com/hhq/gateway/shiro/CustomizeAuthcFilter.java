package com.hhq.gateway.shiro;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import com.hhq.common.util.JsonUtils;
import com.hhq.gateway.constant.CommonConstants;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 登陆状态验证，同时对ajax请求做处理 <Description> <br>
 * 
 * @author yymagicer<br>
 * @version 1.0<br>
 * @taskId <br>
 */
public class CustomizeAuthcFilter extends AuthenticationFilter {
    /**
     * 调测日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomizeAuthcFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        boolean authenticated = super.isAccessAllowed(request, response, mappedValue);
        System.out.println("isAccessAllowed");
        if (authenticated) {
            // 剔除登陆请求的处理
            return handleKickedOutSession(request, response);
        } else {
            // 如果是ajax请求，返回json的响应，提示没有登陆
            writeUnloginReply(response);
            return false;
        }

    }

    /**
     * 判断该会话是否已经被剔除，如果是则退出 Description: <br>
     * 
     * @author minivision<br>
     * @taskId <br>
     * @param request request
     * @param response response
     * @return <br>
     */
    private boolean handleKickedOutSession(ServletRequest request, ServletResponse response) {
        Subject subject = super.getSubject(request, response);
        Session session = subject.getSession();
        Object kickout = session.getAttribute("kickout");
        if (kickout != null) {
            try {
                subject.logout();
            } catch (Exception e) {
                LOGGER.error("failed to logout,", e);
            }
            writeUnloginReply(response);
            return false;

        } else {
            return true;
        }
    }

    /**
     * 写未登录响应 Description: <br>
     * 
     * @author minivision<br>
     * @taskId <br>
     * @param response <br>
     */
    private void writeUnloginReply(ServletResponse response) {
        String resultStr = JsonUtils.getFail(CommonConstants.USER_NOT_LOGIN,CommonConstants.USER_NOT_LOGIN_MSG);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(resultStr);
        } catch (Exception e) {
            LOGGER.error("failed to write response,", e);
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }

}
