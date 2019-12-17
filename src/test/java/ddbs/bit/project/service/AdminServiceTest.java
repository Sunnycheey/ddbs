package ddbs.bit.project.service;

import ddbs.bit.project.dao.entity.Admin;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    @Test
    public void insertAdmin() {
        SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator = new SnowflakeShardingKeyGenerator();
        long id = Long.valueOf(snowflakeShardingKeyGenerator.generateKey().toString());
        int count = 0;
        String username = "lihuichao";
        String password = "p";
        String email = "fuck@bit";
        while(count <= 1000) {
            try {
                adminService.save(new Admin(id, username, password, email));
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            finally {
                count++;
            }
        }
    }
    @Test void selectAdmin() {

        System.out.println(adminService.list().size());
    }
}