package ddbs.bit.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ddbs.bit.project.dao.entity.Admin;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminService {
    List<Admin> getAllAdmin();
}
