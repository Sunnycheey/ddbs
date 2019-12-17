package ddbs.bit.project.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ddbs.bit.project.annotation.UserLoginToken;
import ddbs.bit.project.dao.entity.Admin;
import ddbs.bit.project.dao.entity.User;
import ddbs.bit.project.service.AdminService;
import ddbs.bit.project.service.TokenService;
import ddbs.bit.project.service.UserService;
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
    private UserService userService;
    @Autowired
    TokenService tokenService;

    @PostMapping("userLogin")
    public String login(@RequestBody User user) {
        State state = new State();
        User userForBase = userService.getUserByEmail(user.getEmail());
        if(userForBase == null){
            state.setStateCode(0);
            state.setMessage("登录失败,用户不存在");
            return JSON.toJSONString(state);
        }else {
            if (!userForBase.getPassword().equals(userForBase.getPassword())){
                state.setStateCode(1);
                state.setMessage("密码错误");
                return JSON.toJSONString(state);
            }else {
                String token = tokenService.getToken(userForBase);
                state.setStateCode(2);
                state.setMessage(token);
                return JSON.toJSONString(state);
            }
        }
    }
    @PostMapping("adminLogin")
    public String login(@RequestBody Admin admin){
        State state = new State();
        Admin adminForBase =adminService.getAdminByEmail(admin.getEmail());
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
