package ddbs.bit.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ddbs.bit.project.dao.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class UserServiceTest {
    /*
    @Autowired
    private UserService userService;

    @Test
    public void insertOneUser() {
        Logger logger = LogManager.getLogger(UserServiceTest.class);
        SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator = new SnowflakeShardingKeyGenerator();
        User user = new User();
        long id = Long.valueOf(snowflakeShardingKeyGenerator.generateKey().toString());
        logger.info(String.format("Generated long id is %d", id));
        user.setId(id);
        user.setUsername("lihuichao");
        user.setEmail("lihuichao@bit.com");
        user.setPassword("password");
        userService.save(user);
        logger.info(String.format("Insert singe user %s succeed", user));
    }

    @Test
    public void insertBatchUser(){
        Logger logger = LogManager.getLogger(UserServiceTest.class);
        SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator = new SnowflakeShardingKeyGenerator();
        List<User> userList = new ArrayList<>();
        for(int i = 0; i < 1000; i++) {
            User user = new User();
            long id = Long.valueOf(snowflakeShardingKeyGenerator.generateKey().toString());
            logger.info(String.format("Generated long id is %d", id));
            user.setId(id);
            user.setUsername("lihuichao");
            user.setEmail("lihuichao@bit.com");
            user.setPassword("password");
            userList.add(user);
        }
        userService.saveBatch(userList);
    }

     */
/*
    @Test
    public void getPage() {
        Logger logger = LogManager.getLogger(UserServiceTest.class);
        IPage<User> page = new Page<>(1, 20);
        page = userService.page(page, null);
        logger.info(page.getRecords());
    }
*/
}