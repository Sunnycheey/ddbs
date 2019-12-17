package ddbs.bit.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ddbs.bit.project.dao.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService extends IService<User> {
    User getUserByEmail(String email);

}
