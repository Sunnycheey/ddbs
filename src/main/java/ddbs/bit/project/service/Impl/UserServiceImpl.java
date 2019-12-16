package ddbs.bit.project.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ddbs.bit.project.dao.entity.User;
import ddbs.bit.project.dao.mapper.UserMapper;
import ddbs.bit.project.service.UserService;
import org.apache.shardingsphere.core.parse.autogen.OracleStatementParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: ddbs
 * @description: Service for entity user
 * @author: lihuichao
 * @create: 2019-12-15
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
