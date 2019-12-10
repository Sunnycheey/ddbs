package ddbs.bit.project.controller;

import com.alibaba.fastjson.JSON;
import ddbs.bit.project.dao.entity.Admin;
import ddbs.bit.project.dao.entity.User;
import ddbs.bit.project.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class AdminController {
    @Resource
    private AdminService adminService;
    @RequestMapping(value = "/Admin/{id}")
    public String getAdminByID(@PathVariable long id){
        State s = new State();
        try{
            return JSON.toJSONString(adminService.selectById(id));
        }
        catch(Exception e) {
            e.printStackTrace();
            s.setStateCode(404);
            s.setMessage(e.getMessage());
            return JSON.toJSONString(s);
        }
    }
    @PostMapping(path = "/Admin", consumes = "application/json", produces = "application/json")
    public String insertAdmin(@RequestBody Admin admin) {
        State s = new State();
        try {
            adminService.insertAdmin(admin);
        }
        catch(Exception e) {
            s.setStateCode(404);
            s.setMessage(e.getMessage());
            return JSON.toJSONString(s);
        }
        s.setMessage("ok");
        s.setStateCode(200);
        return JSON.toJSONString(s);
    }
}
