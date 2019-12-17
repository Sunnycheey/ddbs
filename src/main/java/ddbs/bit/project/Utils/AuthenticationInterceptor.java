package ddbs.bit.project.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import ddbs.bit.project.annotation.AdminLoginToken;
import ddbs.bit.project.annotation.PassToken;
import ddbs.bit.project.annotation.UserLoginToken;
import ddbs.bit.project.dao.entity.Admin;
import ddbs.bit.project.dao.entity.User;
import ddbs.bit.project.service.AdminService;
import ddbs.bit.project.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @program: project
 * @description: Class for access control
 * @author: Lihuichao
 * @create: 2019-12-14
 **/

public class AuthenticationInterceptor implements HandlerInterceptor {
    Logger logger = LogManager.getLogger(AuthenticationInterceptor.class);
    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (!passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                String id;
                try {
                    id = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                User user = userService.getById(Long.parseLong(id));
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 验证 token
                else {
                    JWTVerifier jwtVerifierUser = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                    try {
                        // 验证是否以用户的身份登陆
                        jwtVerifierUser.verify(token);
                    } catch (JWTVerificationException e) {
                        logger.warn("Unmatched user id and password!");
                        return false;
                    }
                    return true;
                }
            }
        }
        if(method.isAnnotationPresent(AdminLoginToken.class)){
            AdminLoginToken adminLoginToken = method.getAnnotation(AdminLoginToken.class);
            if(adminLoginToken.required()) {
                if(token == null) {
                    logger.warn(String.format("Requests without token!"));
                    return false;
                }
                String adminId;
                try {
                    adminId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    logger.warn("Post Token cannot be parsed!");
                    return false;
                    // throw new RuntimeException("401");
                }
                Admin admin = adminService.getById(Long.parseLong(adminId));
                JWTVerifier jwtVerifierAdmin = JWT.require(Algorithm.HMAC256(admin.getPassword())).build();
                try {
                    // 先验证是否以用户的身份登陆
                    jwtVerifierAdmin.verify(token);
                }
                catch (JWTVerificationException adminException) {
                    logger.warn(String.format("Requests with wrong password"));
                    return false;
                }
                return true;
            }
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}