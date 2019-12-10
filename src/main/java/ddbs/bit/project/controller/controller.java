package ddbs.bit.project.controller;

import com.alibaba.fastjson.JSON;
import ddbs.bit.project.dao.entity.Admin;
import ddbs.bit.project.dao.entity.User;
import ddbs.bit.project.service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class controller {
    @Resource
    private AdminService adminService;
    @RequestMapping("/")
    public String getGreeting() {
        List<Admin> admin = adminService.getAllAdmin();
        return JSON.toJSONString(admin);
    }
}
