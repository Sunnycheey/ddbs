package ddbs.bit.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ddbs.bit.project.dao.entity.Admin;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService extends IService<Admin> {
    Admin getAdminByEmail(String email);
}
