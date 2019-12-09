package ddbs.bit.project.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ddbs.bit.project.dao.entity.UserInfo;

@RestController
public class controller {
    @RequestMapping("/")
    public String getGreeting() {
        return JSON.toJSONString(new UserInfo("1","liihuichao","122","lihuichaolihc@gmail.com"));
    }
}
