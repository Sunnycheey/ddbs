package ddbs.bit.project.controller;

import com.alibaba.fastjson.JSON;
import ddbs.bit.project.annotation.AdminLoginToken;
import ddbs.bit.project.dao.entity.Admin;
import ddbs.bit.project.dao.entity.User;
import ddbs.bit.project.service.AdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class AdminController {
    Logger logger = LogManager.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;
    @AdminLoginToken
    @RequestMapping(value = "admins")
    public List<Admin> findAdmins() { return adminService.list(); }
    @AdminLoginToken
    @RequestMapping(value = "admin/{id}")
    public Admin findAdminByID(@PathVariable long id){
        Admin admin = adminService.getById(id);
        if(admin == null) {
            logger.warn("Cannot find admin by id %d", id);
            // 异常处理
            return null;
        }
        return admin;
    }
    @AdminLoginToken
    @PostMapping(path = "/admin", consumes = "application/json", produces = "application/json")
    public boolean insertAdmin(@RequestBody Admin admin) throws NoSuchAlgorithmException {
        Admin storedAdmin = adminService.getAdminByEmail(admin.getEmail());
        if(storedAdmin != null) {
            logger.warn("Admin already exist");
            return false;
        }
        String originalPassword = admin.getPassword();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(originalPassword.getBytes(StandardCharsets.UTF_8));
        admin.setHash(hash);
        boolean succeed = adminService.save(admin);
        if(!succeed) {
            logger.warn(String.format("Insert admin %s failed", admin));
            // 异常处理
            return false;
        }
        return true;
    }
}