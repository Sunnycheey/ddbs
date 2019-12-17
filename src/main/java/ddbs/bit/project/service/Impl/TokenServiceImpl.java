package ddbs.bit.project.service.Impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import ddbs.bit.project.dao.entity.Admin;
import ddbs.bit.project.dao.entity.User;
import ddbs.bit.project.service.TokenService;
import org.springframework.stereotype.Service;

/**
 * @program: project
 * @description: Implication of TokenService interface
 * @author: Lihuichao
 * @create: 2019-12-14
 **/
@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String getToken(Admin admin) {
        /**
         * @Description: Generate token for admin
         * @Param: [admin]
         * @return: java.lang.String
         * @Author: Lihuichao
         * @Date: 2019-12-14
         */
        String token = "";
        token = JWT.create().withAudience(Long.toString(admin.getId())).sign(Algorithm.HMAC256(admin.getHash()));
        return token;
    }

    @Override
    public String getToken(User user) {
        String token = "";
        token = JWT.create().withAudience(Long.toString(user.getId())).sign(Algorithm.HMAC256(user.getHash()));
        return token;
    }
}
