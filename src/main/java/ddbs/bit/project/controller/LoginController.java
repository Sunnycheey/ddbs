package ddbs.bit.project.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ddbs.bit.project.annotation.UserLoginToken;
import ddbs.bit.project.dao.entity.Admin;
import ddbs.bit.project.dao.entity.User;
import ddbs.bit.project.service.AdminService;
import ddbs.bit.project.service.TokenService;
import ddbs.bit.project.service.UserService;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    public String login(@RequestBody User user) throws NoSuchAlgorithmException {
        State state = new State();
        User userForBase = userService.getUserByEmail(user.getEmail());
        if(userForBase == null){
            state.setStateCode(0);
            state.setMessage("登录失败,用户不存在");
            return JSON.toJSONString(state);
        }else {
            String originalPassword = user.getPassword();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(originalPassword.getBytes(StandardCharsets.UTF_8));
            String hashHex = Hex.encodeHexString(hash);
            if (!userForBase.getHash().equals(hashHex)){
                state.setStateCode(1);
                state.setMessage("密码错误");
                return JSON.toJSONString(state);
            }
            else {
                String token = tokenService.getToken(userForBase);
                state.setStateCode(2);
                state.setMessage(token);
                return JSON.toJSONString(state);
            }
        }
    }
    @PostMapping("adminLogin")
    public String login(@RequestBody Admin admin) throws NoSuchAlgorithmException {
        State state = new State();
        Admin adminForBase =adminService.getAdminByEmail(admin.getEmail());
        if(adminForBase == null){
            state.setStateCode(0);
            state.setMessage("登录失败,用户不存在");
            return JSON.toJSONString(state);
        }else {
            String originalPassword = admin.getPassword();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(originalPassword.getBytes(StandardCharsets.UTF_8));
            String hashHex = Hex.encodeHexString(hash);
            if (!adminForBase.getHash().equals(hashHex)){
                state.setStateCode(1);
                state.setMessage("密码错误");
                return JSON.toJSONString(state);
            }
            else {
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
