package ddbs.bit.project.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ddbs.bit.project.annotation.UserLoginToken;
import ddbs.bit.project.dao.entity.Admin;
import ddbs.bit.project.service.AdminService;
import ddbs.bit.project.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: project
 * @description: Controller for login activity
 * @author: Lihuichao
 * @create: 2019-12-14
 **/
@RestController
public class LoginController {
    @Autowired
    AdminService adminService;
    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public String login(@RequestBody Admin admin){
        State state = new State();
        Admin adminForBase =adminService.selectById(admin.getId());
        if(adminForBase == null){
            state.setStateCode(0);
            state.setMessage("登录失败,用户不存在");
            return JSON.toJSONString(state);
        }else {
            if (!adminForBase.getPassword().equals(admin.getPassword())){
                state.setStateCode(1);
                state.setMessage("密码错误");
                return JSON.toJSONString(state);
            }else {
                String token = tokenService.getToken(adminForBase);
                state.setStateCode(2);
                state.setMessage(token);
                return JSON.toJSONString(state);
            }
        }
    }
    @UserLoginToken
    @GetMapping("/getAdmin")
    public String getAdmin() {
        return "you really get the token";
    }

}
