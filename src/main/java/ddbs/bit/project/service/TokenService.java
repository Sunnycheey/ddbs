package ddbs.bit.project.service;

import ddbs.bit.project.dao.entity.Admin;

/**
 * @program: IntelliJ IDEA
 * @description: Interface for generate token
 * @author: Lihuichao
 * @create: 2019-12-14
 **/

public interface TokenService {
    String getToken(Admin admin);
}
