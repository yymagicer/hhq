package com.hhq.gateway.shiro;

import com.hhq.customer.constant.CustomerConstant;
import com.hhq.customer.po.CustomerPO;
import com.hhq.customer.service.CustomerService;
import com.hhq.gateway.constant.CommonConstants;
import com.hhq.gateway.customer.vo.LoginVO;
import com.hhq.user.po.UserPO;
import com.hhq.user.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

public class MyShiroRealm extends AuthorizingRealm implements InitializingBean {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private CustomerService customerService;
    /**
     * retryLimitCredentialsMatcher
     */
    @Autowired
    private RetryLimitCredentialsMatcher retryLimitCredentialsMatcher;

    public MyShiroRealm(){
        setName(MyShiroRealm.class.getSimpleName());
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        CustomUsernamePasswordToken token = (CustomUsernamePasswordToken) authenticationToken;
        String userType = token.getUserType();
        SimpleAuthenticationInfo authenticationInfo = null;
        //客户端用户登陆
        if(CommonConstants.USER_TYPE_C.equals(userType)){
            LoginVO loginVO = token.getLoginVO();
            CustomerPO customer = getCustomer(loginVO.getLoginName());
            if(null !=customer) {
                if(CustomerConstant.LOGIN_TYPE_PASS_WORD.equals(loginVO.getLoginType())){
                    authenticationInfo = new SimpleAuthenticationInfo(
                            customer, //用户名
                            customer.getPassword(), //密码
                            new SimpleByteSource(customer.getSalt()),
                            getName()  //realm name
                    );
            }else if(CustomerConstant.LOGIN_TYPE_MOBILE_CODE.equals(loginVO.getLoginType())||CustomerConstant.LOGIN_TYPE_EMAIL_CODE.equals(loginVO.getLoginType())){
                String vCode = new StringBuilder(CommonConstants.HHQ_LOGIN_REDIS_PREFIX).append(loginVO.getBid()).toString();
                String realCode = redisTemplate.opsForValue().get(vCode);
                if(loginVO.getVCode().equals(realCode)){
                    authenticationInfo = new SimpleAuthenticationInfo(customer,null,getName());
                }
            }
            }
        }
        //后台用户登陆
        else if(CommonConstants.USER_TYPE_B.equals(userType)){
            String userName = token.getUsername();
            UserPO user = userService.getByName(userName);
            //用户是否存在
            if (user == null) {
                throw new UnknownAccountException();
            }
            //若存在，将此用户存放到登录认证info中，无需自己做密码对比Shiro会为我们进行密码对比校验
            // 这里盐值可以自定义
                authenticationInfo = new SimpleAuthenticationInfo(
                    user, //用户名
                    user.getPassword(), //密码
                    new SimpleByteSource(user.getSalt()),
                    getName()  //realm name
            );
        }
        return authenticationInfo;
    }

    private CustomerPO getCustomer(String loginName){
        CustomerPO customerPO = customerService.queryByUserName(loginName);
        if(null!=customerPO){
            return customerPO;
        }
        customerPO = customerService.queryByMobile(loginName);
        if(null!=customerPO){
            return customerPO;
        }
        customerPO = customerService.queryByEmail(loginName);
        if(null!=customerPO){
            return customerPO;
        }
        return customerPO;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        this.setCredentialsMatcher(retryLimitCredentialsMatcher);
    }
}
