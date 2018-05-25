package com.example.template.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.template.entity.domain.RoleInfo;
import com.example.template.entity.domain.UserInfo;
import com.example.template.entity.pojo.AuthManagePO;
import com.example.template.entity.pojo.CacheID;
import com.example.template.entity.pojo.RoleEnum;
import com.example.template.entity.pojo.UserRolePO;
import com.example.template.service.impl.RedisServiceImpl;
import com.example.template.util.EventRecordHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private RedisServiceImpl redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("intercept url : " + request.getRequestURL());
        String url = request.getRequestURL().toString();
        //如果用户注册或者登录时,则不拦截
        if (url.contains("login/login") || url.contains("register") || url.contains("swagger")) {
            logger.info("login // register // swagger");
            return true;
        }
        if (StringUtils.equalsIgnoreCase("options", request.getMethod())) {
            response.setStatus(200);
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "*");
            response.addHeader("Access-Control-Allow-Headers", "*");
            logger.info("options method pass it");
            return false;
        }
        String token = StringUtils.isNotBlank(request.getHeader("token")) ? request.getHeader("token")
                : request.getParameter("token");
        if (StringUtils.isBlank(token)) {
            logger.info("token is blank , forbidden");
            response.setStatus(403);
            return false;
        }
        UserInfo userInfo = JSON.parseObject(redisService.getValue(token + CacheID.USER.getDesc()), UserInfo.class);
        RoleInfo roleInfo = JSON.parseObject(redisService.getValue(token + CacheID.ROLE.getDesc()), RoleInfo.class);
        if (userInfo == null || roleInfo == null) {
            logger.info("token is error or has been expired");
            response.setStatus(401);
            return false;
        }
        String requestURI = request.getRequestURI();
        Map map = (Map) ((Map) AuthManagePO.AUTH_MAP.get(RoleEnum.getRoleByDesc(roleInfo.getRoleName()).getName()))
                .get("resourcePermission");
        Set set = map.keySet();
        String auth_url = request.getMethod().toLowerCase() + "," + requestURI;
        if (!set.contains(auth_url)) {
            logger.info("current user has no auth with url : {}", request.getMethod().toLowerCase() + "," + requestURI);
            response.setStatus(403);
            return false;
        }
        UserRolePO userRolePO = new UserRolePO();
        userRolePO.setUserId(userInfo.getId());
        userRolePO.setUserName(userInfo.getUserName());
        userRolePO.setUserFactory(userInfo.getUserFactory());
        userRolePO.setRoleName(roleInfo.getRoleName());
        EventRecordHelper.setUserRolePO(userRolePO);
        logger.info("intercept OK");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        //调用remove清空ThreadLocal缓存
        EventRecordHelper.removeUserRolePO();
        logger.info("intercept finished");
    }
}
