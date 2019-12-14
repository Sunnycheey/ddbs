package ddbs.bit.project.service;

import ddbs.bit.project.dao.entity.Admin;
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
        int count = 4001;
        String username = "lihuichao";
        String password = "p";
        String email = "fuck@bit";
        while(count <= 5000) {
            try {
                adminService.insertAdmin(new Admin(count, username, password, email));
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
        System.out.println(adminService.getAllAdmin().size());
    }
}