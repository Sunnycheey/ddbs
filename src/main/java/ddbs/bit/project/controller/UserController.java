package ddbs.bit.project.controller;

import ddbs.bit.project.dao.entity.User;
import ddbs.bit.project.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @program: ddbs
 * @description: RESTful controller of Users
 * @author: lihuichao
 * @create: 2019-12-16
 **/
@RestController
public class UserController {
    Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @RequestMapping("users")
    public List<User> findUsers() {
        List<User> userList = userService.list();
        if(userList == null) {
            logger.warn(String.format("Cannot get user list"));
            // 异常处理
        }
        return userList;
    }
    @RequestMapping("/users/{id}")
    public User findUserById(@PathVariable long id) {
        User user = userService.getById(id);
        if(user == null) {
            logger.warn(String.format("Cannot get user by id %d", id));
            // 异常处理
        }
        return user;
    }
    @PostMapping(path="/users", consumes = "application/json", produces = "application/json")
    public boolean addUser(@RequestBody User user) throws NoSuchAlgorithmException {
        User storedUser = userService.getUserByEmail(user.getEmail());
        if(storedUser != null) {
            logger.warn("User already exist");
            return false;
        }
        SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator = new SnowflakeShardingKeyGenerator();
        Long id = Long.parseLong(snowflakeShardingKeyGenerator.generateKey().toString());
        user.setId(id);
        String originalPassword = user.getPassword();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(originalPassword.getBytes(StandardCharsets.UTF_8));
        user.setHash(hash);
        boolean succeed = userService.save(user);
        if(!succeed) {
            logger.warn(String.format("Cannot add user %s", user));
            // 异常处理
        }
        return true;
    }
}
