package ddbs.bit.project.service.Impl;

import com.baomidou.mybatisplus.extension.service.IService;
import ddbs.bit.project.dao.entity.Admin;
import ddbs.bit.project.dao.mapper.AdminMapper;
import ddbs.bit.project.service.AdminService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> getAllAdmin() {
        return adminMapper.selectList(null);
    }

    @Override
    public int insertAdmin(Admin admin) {
        return adminMapper.insert(admin);
    }

    @Override
    public Admin selectById(long id) {
        return adminMapper.selectById(id);
    }
}
