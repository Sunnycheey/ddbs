package ddbs.bit.project.dao.mapper;

import ddbs.bit.project.dao.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        List<UserInfo> userInfo = userMapper.getUserByName("lihuichao");
        for(UserInfo info : userInfo) {
            System.out.println(info);
        }
    }
    @Test
    public void testInsert() {
        UserInfo userInfo = new UserInfo("312011114", "lihuichao", "22", "lihuichao");
        if(userMapper.insert(userInfo) == 0) {
            System.out.println("Insert succeed!");
        }
    }
}